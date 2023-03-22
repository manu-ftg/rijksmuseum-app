package com.rijksmuseum.data.repository

import app.cash.turbine.test
import com.rijksmuseum.data.datasource.remote.RijksmuseumRemoteDatasource
import com.rijksmuseum.data.entity.ArtObjectEntity
import com.rijksmuseum.data.entity.ObjectDetailsResponseEntity
import com.rijksmuseum.data.entity.ObjectEntity
import com.rijksmuseum.data.entity.ObjectsResponseEntity
import com.rijksmuseum.data.mapper.toDomain
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RijksmuseumRepositoryTest {

    private lateinit var repository: RijksmuseumRepository

    private lateinit var datasource: RijksmuseumRemoteDatasource

    @Before
    fun setUp() {
        datasource = mockk()
    }

    @Test
    fun whenGetDetailsIsCalledReturnsObjectDetailsModel() = runTest {
        // Given
        val objectDetailsEntity = getObjectDetailsEntity()
        val objectDetailsModel = objectDetailsEntity.toDomain()
        coEvery {
            datasource.getObjectDetails(any())
        } returns ObjectDetailsResponseEntity(artObject = objectDetailsEntity, elapsedMilliseconds = 1)
        val id = "id"

        // When
        repository = RijksmuseumRepositoryImpl(datasource)

        // Then
        repository.getObjectDetails(id).test {
            Assert.assertEquals(objectDetailsModel, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenGetObjectsIsCalledReturnsObjectModelsList() = runTest {
        // Given
        val objectEntityList = getObjectList()
        val objectModelList = objectEntityList.mapNotNull { it.toDomain() }
        coEvery {
            datasource.getObjects(any())
        } returns ObjectsResponseEntity(artObjects = getObjectList())
        val page = 1

        // When
        repository = RijksmuseumRepositoryImpl(datasource)

        // Then
        repository.getObjects(page).test {
            Assert.assertEquals(objectModelList, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
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

    private fun getObjectList() = listOf(
        ObjectEntity(id = "id1", objectNumber = "number", title = "title", principalOrFirstMaker = "artist"),
        ObjectEntity(id = "id2", objectNumber = "number", title = "title", principalOrFirstMaker = "artist"),
        ObjectEntity(id = "id3", objectNumber = "number", title = "title", principalOrFirstMaker = "artist")
    )
}
