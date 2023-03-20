package com.rijksmuseum.data.mapper

import com.rijksmuseum.data.entity.ObjectEntity
import com.rijksmuseum.domain.model.ObjectModel

object DomainMapper {
    fun ObjectEntity.toDomain(): ObjectModel? {
        return if (objectNumber != null && id != null && longTitle != null && title != null) {
            ObjectModel(
                imageUrl = headerImage?.url,
                id = id,
                longTitle = longTitle,
                objectNumber = objectNumber,
                title = title
            )
        } else {
            null
        }
    }
}
