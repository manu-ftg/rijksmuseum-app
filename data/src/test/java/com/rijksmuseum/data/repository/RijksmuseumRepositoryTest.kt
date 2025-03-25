package com.rijksmuseum.data.repository

import com.rijksmuseum.data.datasource.local.RijksmuseumLocalDatasource
import com.rijksmuseum.data.datasource.remote.RijksmuseumRemoteDatasource
import com.rijksmuseum.data.entity.ArtObjectEntity
import com.rijksmuseum.data.entity.ObjectDetailsResponseEntity
import com.rijksmuseum.data.entity.ObjectEntity
import com.rijksmuseum.data.entity.ObjectsResponseEntity
import com.rijksmuseum.data.mapper.toDomain
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.model.PageDataModel
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

    private lateinit var remoteDatasource: RijksmuseumRemoteDatasource

    private lateinit var localDatasource: RijksmuseumLocalDatasource

    @Before
    fun setUp() {
        remoteDatasource = mockk()
        localDatasource = mockk()
    }

    @Test
    fun whenGetDetailsIsCalledReturnsObjectDetailsModel() = runTest {
        // Given
        val objectDetailsEntity = getObjectDetailsEntity()
        val objectDetailsModel = objectDetailsEntity.toDomain()
        coEvery {
            remoteDatasource.getObjectDetails(any())
        } returns ObjectDetailsResponseEntity(artObject = objectDetailsEntity, elapsedMilliseconds = 1)
        val id = "id"

        // When
        repository = RijksmuseumRepositoryImpl(remoteDatasource, localDatasource)

        // Then
        val result = repository.getObjectDetails(id)
        Assert.assertEquals(Result.success(objectDetailsModel), result)
    }

    @Test
    fun whenGetDetailsIsCalledReturnsError() = runTest {
        // Given
        val error = Throwable()
        coEvery {
            remoteDatasource.getObjectDetails(any())
        } throws error
        val id = "id"

        // When
        repository = RijksmuseumRepositoryImpl(remoteDatasource, localDatasource)

        // Then
        val result = repository.getObjectDetails(id)
        Assert.assertEquals(Result.failure<ObjectDetailsModel>(error), result)
    }

    @Test
    fun whenGetObjectsIsCalledReturnsObjectModelsList() = runTest {
        // Given
        val objectEntityList = getObjectList()
        val objectModelList = objectEntityList.mapNotNull { it.toDomain() }
        coEvery {
            remoteDatasource.getObjects(any())
        } returns ObjectsResponseEntity(artObjects = getObjectList())
        val page = 1

        // When
        repository = RijksmuseumRepositoryImpl(remoteDatasource, localDatasource)

        // Then
        val result = repository.getObjects(page)
        Assert.assertEquals(PageDataModel.NewData(page, objectModelList), result)
    }

    @Test
    fun whenGetObjectsIsCalledReturnsNoMoreItems() = runTest {
        // Given
        coEvery {
            remoteDatasource.getObjects(any())
        } returns ObjectsResponseEntity(artObjects = getObjectList())
        val page = 1001

        // When
        repository = RijksmuseumRepositoryImpl(remoteDatasource, localDatasource)

        // Then
        val result = repository.getObjects(page)
        Assert.assertEquals(PageDataModel.EndOfData, result)
    }

    @Test
    fun whenGetObjectsIsCalledReturnError() = runTest {
        // Given
        val error = Throwable()
        coEvery {
            remoteDatasource.getObjects(any())
        } throws error
        val page = 1

        // When
        repository = RijksmuseumRepositoryImpl(remoteDatasource, localDatasource)

        // Then
        val result = repository.getObjects(page)
        Assert.assertEquals(PageDataModel.Error(error), result)
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
