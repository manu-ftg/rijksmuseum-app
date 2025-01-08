package com.rijksmuseum.presentation.viewdata

sealed class ObjectItemViewData(val key: String) {
    data class HeaderItem(val artist: String, val id: String = "id"): ObjectItemViewData(key = "${artist}_$id")

    object LoaderItem: ObjectItemViewData(key = "loader_key")

    data class ObjectItem(
        val id: String,
        val title: String,
        val artist: String,
        val objectNumber: String,
        val imageUrl: String? = null
    ): ObjectItemViewData(objectNumber)
}
