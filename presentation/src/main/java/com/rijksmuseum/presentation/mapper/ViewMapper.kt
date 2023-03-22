package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.viewdata.ObjectViewData
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData

fun buildObjectItemsList(oldList: List<ObjectItemViewData>, newItems: List<ObjectModel>): List<ObjectItemViewData> {
    val filteredList = oldList.filter { it !is ObjectItemViewData.LoaderItem }
    return buildList {
        addAll(filteredList)
        newItems.map { it.toViewData() }.forEachIndexed { index, objectItem ->
            if (index == 0 && filteredList.isEmpty()
                || index == 0 && (filteredList.last() as? ObjectItemViewData.ObjectItem)?.artist != objectItem.artist
                || index > 0 && newItems[index - 1].artist != objectItem.artist
            ) {
                add(ObjectItemViewData.HeaderItem(objectItem.artist, objectItem.id))
            }
            add(objectItem)
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
