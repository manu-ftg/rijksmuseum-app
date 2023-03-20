package com.rijksmuseum.presentation.display

sealed class ObjectItemDisplay {
    data class HeaderItem(val artist: String): ObjectItemDisplay()

    object LoaderItem: ObjectItemDisplay()

    data class ObjectItem(
        val id: String,
        val title: String,
        val artist: String,
        val objectNumber: String,
        val imageUrl: String? = null
    ): ObjectItemDisplay()
}
