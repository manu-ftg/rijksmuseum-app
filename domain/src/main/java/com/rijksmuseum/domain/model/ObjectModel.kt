package com.rijksmuseum.domain.model

data class ObjectModel(
    val id: String,
    val imageUrl: String?,
    val longTitle: String,
    val objectNumber: String,
    val title: String,
    val artist: String
)
