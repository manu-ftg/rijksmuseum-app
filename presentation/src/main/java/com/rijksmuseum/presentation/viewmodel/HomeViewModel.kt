package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.model.PageDataModel
import com.rijksmuseum.domain.usecase.GetObjectsListPageUseCase
import com.rijksmuseum.presentation.mapper.toViewData
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getObjectsListPageUseCase: GetObjectsListPageUseCase,
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
        when (val currentState = _state.value) {
            is ScreenState.Loaded -> {
                if (currentState.content.shouldLoadMoreItems) {
                    _state.update {
                        ScreenState.Loaded(
                            currentState.content.copy(
                                isLoadingMore = true,
                                showError = false
                            )
                        )
                    }
                } else {
                    return
                }
            }
            else -> {
                _state.update {
                    ScreenState.Loading
                }
            }
        }
        viewModelScope.launch {
            val result = getObjectsListPageUseCase.execute(currentPage?.let { it + 1 })
            _state.update { currentState ->
                when (result) {
                    is PageDataModel.NewData -> {
                        currentPage = result.page
                        ScreenState.Loaded(
                            HomeState(objectsList = getUpdatedList(result.items))
                        )
                    }
                    PageDataModel.EndOfData -> {
                        (currentState as? ScreenState.Loaded)?.content?.let { content ->
                            ScreenState.Loaded(
                                content.copy(isLoadingMore = false, moreObjectsAvailable = false)
                            )
                        } ?: ScreenState.Error()
                    }
                    is PageDataModel.Error -> {
                        when (currentState) {
                            is ScreenState.Loaded -> {
                                ScreenState.Loaded(
                                    currentState.content.copy(isLoadingMore = false, showError = true)
                                )
                            }
                            else -> {
                                ScreenState.Error()
                            }
                        }
                    }
                }
            }
        }
    }

    fun onLoadingItemReached() {
        val shouldLoadMoreItems = (_state.value as? ScreenState.Loaded<HomeState>)?.content?.let { !it.isLoadingMore && it.moreObjectsAvailable } == true
        if (shouldLoadMoreItems) {
            loadObjects()
        }
    }

    fun onObjectClicked(objectNumber: String) {
        viewModelScope.launch {
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

    private fun getUpdatedList(newList: List<ObjectModel>): List<ObjectItemViewData> {
        var lastItem: ObjectItemViewData? = null
        return buildList {
            (_state.value as? ScreenState.Loaded)?.content?.objectsList?.also { currentList ->
                lastItem = currentList.last()
                addAll(currentList)
            }
            newList.forEach { item ->
                if (lastItem == null || (lastItem as? ObjectItemViewData.ObjectItem)?.artist != item.artist) {
                    add(ObjectItemViewData.HeaderItem(item.artist))
                }
                item.toViewData().also { viewData ->
                    lastItem = viewData
                    add(viewData)
                }
            }
        }.distinctBy { it.key }
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
