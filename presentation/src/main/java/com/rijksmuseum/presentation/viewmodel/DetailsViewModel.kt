package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.domain.usecase.GetObjectDetailsUseCase
import com.rijksmuseum.presentation.display.ObjectDisplay
import com.rijksmuseum.presentation.display.ScreenState
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

    private val _state = MutableStateFlow<ScreenState<ObjectDisplay>>(ScreenState.Loading)
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
                        ScreenState.Loading
                    }
                }
                .map { objectDetails ->
                    ScreenState.Loaded(objectDetails.toDisplay())
                }
                .catch<ScreenState<ObjectDisplay>> {
                    emit(ScreenState.Error())
                }
                .collect(_state)
        }
    }
}
