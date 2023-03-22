package com.rijksmuseum.presentation.viewdata

data class ObjectViewData(
    val id: String,
    val imageUrl: String?,
    val title: String,
    val subtitle: String,
    val artist: String,
    val description: String?,
    val documentation: List<String>,
    val physicalMedium: String?,
)
