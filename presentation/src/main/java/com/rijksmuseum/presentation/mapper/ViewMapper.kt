package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.display.ObjectItemDisplay

fun Map<String, List<ObjectModel>>.toList(): List<ObjectItemDisplay> {
    return buildList {
        keys.forEach { artist ->
            add(ObjectItemDisplay.HeaderItem(artist))
            get(artist)?.forEach { objectModel ->
                add(
                    ObjectItemDisplay.ObjectItem(
                        id = objectModel.id,
                        title = objectModel.title,
                        artist = objectModel.artist,
                        imageUrl = objectModel.imageUrl,
                        objectNumber = objectModel.objectNumber
                    )
                )
            }
        }
        add(ObjectItemDisplay.LoaderItem)
    }
}
