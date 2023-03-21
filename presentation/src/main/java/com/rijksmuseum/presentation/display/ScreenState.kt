package com.rijksmuseum.presentation.display

sealed class ScreenState<out T> {
    object Loading: ScreenState<Nothing>()

    data class Error(val message: String? = null): ScreenState<Nothing>()

    data class Loaded<out T>(val content: T): ScreenState<T>()
}
