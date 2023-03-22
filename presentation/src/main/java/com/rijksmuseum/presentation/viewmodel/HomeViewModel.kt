package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.usecase.GetObjectsListUseCase
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import com.rijksmuseum.presentation.mapper.toList
import com.rijksmuseum.presentation.util.DefaultDispatcherProvider
import com.rijksmuseum.presentation.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getObjectsListUseCase: GetObjectsListUseCase,
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    private val _state = MutableStateFlow<ScreenState<HomeState>>(ScreenState.Loading)
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<HomeEvent>()
    val events = _events.asSharedFlow()

    private val _objectsMap = MutableStateFlow(mutableMapOf<String, MutableList<ObjectModel>>())
    private val _currentPage = MutableStateFlow(INITIAL_PAGE)

    init {
        initialize()
    }

    private fun initialize() {
        _state.update { ScreenState.Loading }
        _currentPage.update { INITIAL_PAGE }
        _objectsMap.update { mutableMapOf() }
        loadObjects()
    }

    private fun loadObjects() {
        viewModelScope.launch(dispatcherProvider.io) {
            getObjectsListUseCase.execute(_currentPage.value)
                .onStart {
                    _state.update {
                        when (val currentState = _state.value) {
                            is ScreenState.Loaded -> {
                                ScreenState.Loaded(
                                    currentState.content.copy(
                                        isLoadingMore = true,
                                        showError = false
                                    )
                                )
                            }
                            else -> {
                                ScreenState.Loading
                            }
                        }
                    }
                }
                .map { objects ->
                    objects.forEach { objectItem ->
                        if (_objectsMap.value.containsKey(objectItem.artist)) {
                            _objectsMap.value[objectItem.artist]?.add(objectItem)
                        } else {
                            _objectsMap.value[objectItem.artist] = mutableListOf(objectItem)
                        }
                    }
                    _currentPage.update {
                        _currentPage.value + 1
                    }
                    ScreenState.Loaded(
                        HomeState(objectsList = _objectsMap.value.toList())
                    )
                }.catch<ScreenState<HomeState>> {
                    emit(
                        when (val currentState = _state.value) {
                            is ScreenState.Loaded -> {
                                ScreenState.Loaded(
                                    currentState.content.copy(
                                        isLoadingMore = false,
                                        showError = true
                                    )
                                )
                            }
                            else -> {
                                ScreenState.Error()
                            }
                        }
                    )
                }.collect(_state)
        }
    }

    fun onLoadingItemReached() {
        if ((_state.value as? ScreenState.Loaded<HomeState>)?.content?.isLoadingMore == false) {
            loadObjects()
        }
    }

    fun onObjectClicked(objectNumber: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            _events.emit(HomeEvent.NavigateToDetail(objectNumber))
        }
    }

    fun onRetryClicked() {
        if ((_state.value as? ScreenState.Loaded<HomeState>)?.content?.isLoadingMore == false || _state.value !is ScreenState.Loading) {
            loadObjects()
        }
    }

    fun onDialogDismissed() {
        (_state.value as? ScreenState.Loaded)?.content?.let { homeState ->
            _state.update {
                ScreenState.Loaded(
                    homeState.copy(
                        showError = false
                    )
                )
            }
        }
    }
}

data class HomeState(
    val isLoadingMore: Boolean = false,
    val objectsList: List<ObjectItemViewData> = listOf(),
    val showError: Boolean = false
)

sealed class HomeEvent {
    data class NavigateToDetail(val objectNumber: String): HomeEvent()
}
