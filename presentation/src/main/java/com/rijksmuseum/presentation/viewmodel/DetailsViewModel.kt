package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.domain.usecase.GetObjectDetailsUseCase
import com.rijksmuseum.presentation.mapper.toViewData
import com.rijksmuseum.presentation.util.DefaultDispatcherProvider
import com.rijksmuseum.presentation.util.DispatcherProvider
import com.rijksmuseum.presentation.viewdata.ObjectViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
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
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getObjectDetailsUseCase: GetObjectDetailsUseCase,
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<ObjectViewData>>(ScreenState.Loading)
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<DetailsEvent>()
    val events = _events.asSharedFlow()

    private val id: String by lazy {
        savedStateHandle.get<String>("objectId")
            ?: throw IllegalArgumentException("No objectId provided")
    }

    init {
        loadObjectDetails(id)
    }

    private fun loadObjectDetails(objectNumber: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            getObjectDetailsUseCase.execute(objectNumber)
                .onStart {
                    _state.update {
                        ScreenState.Loading
                    }
                }
                .map { objectDetails ->
                    ScreenState.Loaded(objectDetails.toViewData())
                }
                .catch<ScreenState<ObjectViewData>> {
                    emit(ScreenState.Error())
                }
                .collect(_state)
        }
    }

    fun onDialogClicked() {
        _state.update {
            ScreenState.Loading
        }
        viewModelScope.launch(dispatcherProvider.main) {
            _events.emit(DetailsEvent.NavigateBack)
        }
    }
}

sealed class DetailsEvent {
    object NavigateBack: DetailsEvent()
}
