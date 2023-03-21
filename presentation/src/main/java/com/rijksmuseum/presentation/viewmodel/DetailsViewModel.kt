package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.domain.usecase.GetObjectDetailsUseCase
import com.rijksmuseum.presentation.display.ObjectDisplay
import com.rijksmuseum.presentation.mapper.toDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val getObjectDetailsUseCase: GetObjectDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val state = _state.asStateFlow()

    private val id: String by lazy {
        savedStateHandle.get<String>("objectId")
            ?: throw IllegalArgumentException("No objectId provided")
    }

    init {
        loadObjectDetails(id)
    }

    private fun loadObjectDetails(objectNumber: String) {
        viewModelScope.launch {
            getObjectDetailsUseCase.execute(objectNumber)
                .onStart {
                    _state.update {
                        DetailsState.Loading
                    }
                }
                .map { objectDetails ->
                    DetailsState.Loaded(objectDetails.toDisplay())
                }
                .catch<DetailsState> { throwable ->
                    emit(DetailsState.Error(throwable.message ?: ""))
                }
                .collect(_state)
        }
    }
}

sealed class DetailsState {
    object Loading: DetailsState()

    data class Error(val message: String): DetailsState()

    data class Loaded(val objectDisplay: ObjectDisplay): DetailsState()
}
