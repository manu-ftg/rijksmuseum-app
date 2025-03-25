package com.rijksmuseum.data.mapper

import com.rijksmuseum.data.entity.ArtObjectEntity
import com.rijksmuseum.data.entity.ObjectEntity
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel

fun ObjectEntity.toDomain(): ObjectModel? {
    return try {
        ObjectModel(
            imageUrl = headerImage?.url,
            id = requireNotNull(id),
            objectNumber = requireNotNull(objectNumber),
            title = requireNotNull(title),
            artist = requireNotNull(principalOrFirstMaker)
        )
    } catch (_: Throwable) {
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
