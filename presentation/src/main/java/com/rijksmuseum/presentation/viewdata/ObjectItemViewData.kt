package com.rijksmuseum.presentation.viewdata

sealed class ObjectItemViewData() {
    data class HeaderItem(val artist: String): ObjectItemViewData()

    object LoaderItem: ObjectItemViewData()

    data class ObjectItem(
        val id: String,
        val title: String,
        val artist: String,
        val objectNumber: String,
        val imageUrl: String? = null
    ): ObjectItemViewData()
}
