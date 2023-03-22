package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.display.ObjectDisplay
import com.rijksmuseum.presentation.display.ObjectItemDisplay

fun Map<String, List<ObjectModel>>.toList(): List<ObjectItemDisplay> {
    return buildList {
        keys.sorted().forEach { artist ->
            add(ObjectItemDisplay.HeaderItem(artist))
            addAll(
                get(artist)?.map {
                        objectModel ->
                    objectModel.toDisplay()
                } ?: listOf()
            )
        }
        add(ObjectItemDisplay.LoaderItem)
    }.distinctBy { it.key }
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

fun ObjectDetailsModel.toDisplay(): ObjectDisplay {
    return ObjectDisplay(
        id = id,
        imageUrl = imageUrl,
        title = title,
        subtitle = subtitle,
        artist = artist,
        description = description,
        documentation = documentation,
        physicalMedium = physicalMedium
    )
}
