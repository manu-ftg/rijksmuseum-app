package com.rijksmuseum.data.mapper

import com.rijksmuseum.data.entity.ObjectEntity
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
