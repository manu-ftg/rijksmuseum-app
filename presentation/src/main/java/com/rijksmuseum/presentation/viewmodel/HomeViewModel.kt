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

    private val _objectsMap = MutableStateFlow(mutableMapOf<String, MutableList<ObjectModel>>())

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
                    objects.forEach { objectItem ->
                        if (_objectsMap.value.containsKey(objectItem.artist)) {
                            _objectsMap.value[objectItem.artist]?.add(objectItem)
                        } else {
                            _objectsMap.value[objectItem.artist] = mutableListOf(objectItem)
                        }
                    }
                    _state.value.copy(
                        isLoading = false,
                        isPaging = false,
                        currentPage = _state.value.currentPage + 1,
                        objectsList = _objectsMap.value.toList()
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
    val isLoading: Boolean = false,
    val isPaging: Boolean = false,
    val currentPage: Int = 1,
    val objectsList: List<ObjectItemDisplay> = listOf(),
)
