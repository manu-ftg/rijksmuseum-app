package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.viewdata.ObjectViewData
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData

fun Map<String, List<ObjectModel>>.toList(): List<ObjectItemViewData> {
    return buildList {
        keys.sorted().forEach { artist ->
            add(ObjectItemViewData.HeaderItem(artist))
            addAll(
                get(artist)?.map {
                        objectModel ->
                    objectModel.toViewData()
                } ?: listOf()
            )
        }
        add(ObjectItemViewData.LoaderItem)
    }.distinctBy { it.key }
}

fun ObjectModel.toViewData(): ObjectItemViewData.ObjectItem {
    return ObjectItemViewData.ObjectItem(
        id = id,
        title = title,
        artist = artist,
        imageUrl = imageUrl,
        objectNumber = objectNumber
    )
}

fun ObjectDetailsModel.toViewData(): ObjectViewData {
    return ObjectViewData(
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
