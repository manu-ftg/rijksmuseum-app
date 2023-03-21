package com.rijksmuseum.data.mapper

import com.rijksmuseum.data.entity.ArtObjectEntity
import com.rijksmuseum.data.entity.ObjectEntity
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel

fun ObjectEntity.toDomain(): ObjectModel? {
    return if (objectNumber != null && id != null && longTitle != null && title != null && principalOrFirstMaker != null) {
        ObjectModel(
            imageUrl = headerImage?.url,
            id = id,
            longTitle = longTitle,
            objectNumber = objectNumber,
            title = title,
            artist = principalOrFirstMaker
        )
    } else {
        null
    }
}

fun ArtObjectEntity.toDomain(): ObjectDetailsModel {
    return ObjectDetailsModel(
        id = id,
        imageUrl = webImage?.url,
        title = longTitle ?: title,
        subtitle = subTitle,
        description = description,
        artist = principalOrFirstMaker,
        documentation = documentation ?: listOf(),
        physicalMedium = physicalMedium
    )
}
