package com.rijksmuseum.domain.usecase

import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.model.PageDataModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetObjectsListPageUseCaseTest {

    private lateinit var repository: RijksmuseumRepository

    private lateinit var useCase: GetObjectsListPageUseCase

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun whenUseCaseIsExecutedRepositoryIsCalled() = runTest {
        // Given
        val page = 1
        val result = PageDataModel.NewData(page, getObjectsList())
        coEvery {
            repository.getObjects(any())
        } returns result

        // When
        useCase = GetObjectsListPageUseCase(repository)
        useCase.execute(page)

        // Then
        coVerify {
            repository.getObjects(page)
        }
    }

    @Test
    fun whenUseCaseIsExecutedUseCaseReturnsObjectsList() = runTest {
        // Given
        val page = 1
        val result = PageDataModel.NewData(page, getObjectsList())
        coEvery {
            repository.getObjects(any())
        } returns result

        // When
        useCase = GetObjectsListPageUseCase(repository)

        // Then
        assertEquals(PageDataModel.NewData(page, getObjectsList()), useCase.execute(page))
    }

    @After
    fun tearDown() {
    }

    private fun getObjectsList() = listOf(
        ObjectModel("id1", "url", "number1", "title", "artistA"),
        ObjectModel("id2", "url","number2", "title", "artistA"),
        ObjectModel("id3", "url", "number3", "title", "artistB")
    )
}
