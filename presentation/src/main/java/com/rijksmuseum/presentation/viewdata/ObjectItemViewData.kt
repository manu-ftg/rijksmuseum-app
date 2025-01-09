package com.rijksmuseum.presentation.viewdata

sealed class ObjectItemViewData(val key: String) {
    data class HeaderItem(val artist: String): ObjectItemViewData(key = "${artist}_key")

    object LoaderItem: ObjectItemViewData(key = "loader_key")

    data class ObjectItem(
        val id: String,
        val title: String,
        val artist: String,
        val objectNumber: String,
        val imageUrl: String? = null
    ): ObjectItemViewData(key = id)
}
