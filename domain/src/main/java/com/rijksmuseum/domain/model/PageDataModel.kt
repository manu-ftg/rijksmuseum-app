package com.rijksmuseum.domain.model

sealed class PageDataModel<out T> {
    data class NewData<out T>(
        val page: Int,
        val items: List<T>
    ): PageDataModel<T>()

    object EndOfData: PageDataModel<Nothing>()

    data class Error(val error: Throwable): PageDataModel<Nothing>()
}
