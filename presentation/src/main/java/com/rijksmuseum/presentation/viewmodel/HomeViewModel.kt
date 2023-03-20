package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.usecase.GetObjectsListUseCase
import com.rijksmuseum.presentation.display.ObjectItemDisplay
import com.rijksmuseum.presentation.mapper.toList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getObjectsListUseCase: GetObjectsListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val objectsMap = mutableMapOf<String, MutableList<ObjectModel>>()

    init {
        initialize()
    }

    private fun initialize() {
        _state.update { HomeState() }
        loadObjects(showLoader = true)
    }

    private fun loadObjects(showLoader: Boolean = false) {
        viewModelScope.launch {
            getObjectsListUseCase.execute(_state.value.currentPage)
                .onStart {
                    if (showLoader) {
                        _state.update {
                            _state.value.copy(isLoading = true)
                        }
                    }
                    _state.update {
                        _state.value.copy(isPaging = true)
                    }
                }
                .map { objects ->
                    objects.forEach {
                        if (objectsMap.containsKey(it.artist)) {
                            objectsMap[it.artist]?.add(it)
                        } else {
                            objectsMap[it.artist] = mutableListOf(it)
                        }
                    }
                    _state.value.copy(
                        objectsList = objectsMap.toList(),
                        isLoading = false,
                        isPaging = false,
                        currentPage = _state.value.currentPage + 1
                    )
                }.collect(_state)
        }
    }

    fun onLastItemReached() {
        if (_state.value.isPaging.not()) {
            loadObjects(showLoader = false)
        }
    }
}

data class HomeState(
    val objectsList: List<ObjectItemDisplay> = emptyList(),
    val isLoading: Boolean = false,
    val isPaging: Boolean = false,
    val currentPage: Int = 0
)
