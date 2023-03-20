package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.display.ObjectItemDisplay

fun Map<String, List<ObjectModel>>.toList(): List<ObjectItemDisplay> {
    return buildList {
        keys.forEach { artist ->
            add(ObjectItemDisplay.HeaderItem(artist))
            addAll(
                get(artist)?.map {
                        objectModel ->
                    objectModel.toDisplay()
                } ?: listOf()
            )
        }
        add(ObjectItemDisplay.LoaderItem)
    }
}

fun ObjectModel.toDisplay(): ObjectItemDisplay.ObjectItem {
    return ObjectItemDisplay.ObjectItem(
        id = id,
        title = title,
        artist = artist,
        imageUrl = imageUrl,
        objectNumber = objectNumber
    )
}
