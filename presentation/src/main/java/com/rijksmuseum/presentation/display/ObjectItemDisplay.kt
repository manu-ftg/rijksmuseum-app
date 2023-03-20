package com.rijksmuseum.presentation.display

sealed class ObjectItemDisplay(val key: String) {
    data class HeaderItem(val artist: String): ObjectItemDisplay("${artist}_key")

    object LoaderItem: ObjectItemDisplay("loader_key")

    data class ObjectItem(
        val id: String,
        val title: String,
        val artist: String,
        val objectNumber: String,
        val imageUrl: String? = null
    ): ObjectItemDisplay(id)
}
