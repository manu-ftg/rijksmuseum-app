package com.rijksmuseum.presentation.mapper

import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.presentation.viewdata.ObjectViewData

fun buildObjectItemsList(oldList: List<ObjectItemViewData>?, newItems: List<ObjectModel>): List<ObjectItemViewData> {
    var lastItem: ObjectItemViewData? = null
    return buildList {
        oldList?.filter { it !is ObjectItemViewData.LoaderItem }?.also { currentList ->
            lastItem = currentList.last()
            addAll(currentList)
        }
        newItems.forEach { item ->
            if (lastItem == null || (lastItem as? ObjectItemViewData.ObjectItem)?.artist != item.artist) {
                add(ObjectItemViewData.HeaderItem(item.artist))
            }
            item.toViewData().also { viewData ->
                lastItem = viewData
                add(viewData)
            }
        }
        add(ObjectItemViewData.LoaderItem)
    }
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
