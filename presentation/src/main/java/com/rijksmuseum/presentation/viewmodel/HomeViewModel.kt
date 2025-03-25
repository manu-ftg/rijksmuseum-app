package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.domain.model.PageDataModel
import com.rijksmuseum.domain.usecase.GetObjectsListPageUseCase
import com.rijksmuseum.presentation.mapper.buildObjectItemsList
import com.rijksmuseum.presentation.util.DispatcherProvider
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getObjectsListPageUseCase: GetObjectsListPageUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<HomeState>>(ScreenState.Loading)
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<HomeEvent>()
    val events = _events.asSharedFlow()

    private var currentPage: Int? = null

    init {
        loadObjects()
    }

    private fun loadObjects() {
        if (shouldLoadMoreItems()) {
            _state.update { currentState ->
                when (currentState) {
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

            viewModelScope.launch(dispatcherProvider.main) {
                val newState = withContext(dispatcherProvider.io) {
                    val result = getObjectsListPageUseCase.execute(currentPage?.let { it + 1 })
                    val currentState = _state.value
                    when (result) {
                        is PageDataModel.NewData -> {
                            currentPage = result.page
                            ScreenState.Loaded(
                                HomeState(
                                    objectsList = buildObjectItemsList(
                                        oldList = (currentState as? ScreenState.Loaded)?.content?.objectsList,
                                        newItems = result.items
                                    )
                                )
                            )
                        }
                        PageDataModel.EndOfData -> {
                            (currentState as? ScreenState.Loaded)?.content?.let { content ->
                                ScreenState.Loaded(
                                    content.copy(
                                        objectsList = content.objectsList.filter { it !is ObjectItemViewData.LoaderItem },
                                        isLoadingMore = false,
                                        moreObjectsAvailable = false
                                    )
                                )
                            } ?: ScreenState.Error()
                        }
                        is PageDataModel.Error -> {
                            (currentState as? ScreenState.Loaded)?.content?.let { content ->
                                ScreenState.Loaded(
                                    content.copy(isLoadingMore = false, showError = true)
                                )
                            } ?: ScreenState.Error()
                        }

                    }
                }
                _state.update { newState }
            }
        }
    }

    fun onLoadingItemReached() {
        loadObjects()
    }

    fun onObjectClicked(objectNumber: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            _events.emit(HomeEvent.NavigateToDetail(objectNumber))
        }
    }

    fun onRetryClicked() {
        loadObjects()
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

    private fun shouldLoadMoreItems(): Boolean {
        return (_state.value as? ScreenState.Loaded)?.content?.shouldLoadMoreItems ?: true
    }
}

data class HomeState(
    val isLoadingMore: Boolean = false,
    val moreObjectsAvailable: Boolean = true,
    val objectsList: List<ObjectItemViewData> = listOf(),
    val showError: Boolean = false
) {
    val shouldLoadMoreItems: Boolean get() {
        return !isLoadingMore && moreObjectsAvailable
    }
}

sealed class HomeEvent {
    data class NavigateToDetail(val objectNumber: String): HomeEvent()
}
