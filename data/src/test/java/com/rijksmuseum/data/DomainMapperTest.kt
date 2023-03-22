package com.rijksmuseum.data

import com.rijksmuseum.data.entity.ArtObjectEntity
import com.rijksmuseum.data.entity.ObjectEntity
import com.rijksmuseum.data.mapper.toDomain
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.ObjectModel
import org.junit.Assert
import org.junit.Test

class DomainMapperTest {
    @Test
    fun objectDetailsEntityMappedToModelCorrectly() {
        val detailsEntity = getObjectDetailsEntity()
        val detailsModel = getObjectDetailsModel()
        Assert.assertEquals(detailsModel, detailsEntity.toDomain())
    }

    @Test
    fun objectsEntityMappedToModelsListCorrectly() {
        val objectEntitiesList = getObjectEntityList()
        val objectModelsList = getObjectModelList()
        Assert.assertEquals(objectModelsList, objectEntitiesList.map { it.toDomain() })
    }

    @Test
    fun whenEntityHasNotRequiredParameterReturnNull() {
        val incompleteEntityObject = ObjectEntity()
        Assert.assertEquals(null, incompleteEntityObject.toDomain())
    }

    private fun getObjectDetailsEntity() = ArtObjectEntity(
        id = "id",
        title = "title",
        documentation = listOf(),
        description = "description",
        physicalMedium = "medium",
        language = "language",
        objectNumber = "number",
        principalMaker = "artist",
        principalOrFirstMaker = "artist",
        subTitle = "subtitle"
    )

    private fun getObjectDetailsModel() = ObjectDetailsModel(
        id = "id",
        title = "title",
        documentation = listOf(),
        description = "description",
        physicalMedium = "medium",
        artist = "artist",
        imageUrl = null,
        subtitle = "subtitle"
    )

    private fun getObjectEntityList() = listOf(
        ObjectEntity(id = "id1", objectNumber = "number", title = "title", principalOrFirstMaker = "artist"),
        ObjectEntity(id = "id2", objectNumber = "number", title = "title", principalOrFirstMaker = "artist"),
        ObjectEntity(id = "id3", objectNumber = "number", title = "title", principalOrFirstMaker = "artist")
    )

    private fun getObjectModelList() = listOf(
        ObjectModel(id = "id1", objectNumber = "number", title = "title", artist = "artist", imageUrl = null),
        ObjectModel(id = "id2", objectNumber = "number", title = "title", artist = "artist", imageUrl = null),
        ObjectModel(id = "id3", objectNumber = "number", title = "title", artist = "artist", imageUrl = null)
    )
}
