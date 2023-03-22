package com.rijksmuseum.domain.usecase

import app.cash.turbine.test
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetObjectsListUseCaseTest {

    private lateinit var repository: RijksmuseumRepository

    private lateinit var useCase: GetObjectsListUseCase

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun whenUseCaseIsExecutedRepositoryIsCalled() = runTest {
        // Given
        val flow = flow {
            emit(getObjectsList())
        }
        coEvery {
            repository.getObjects(any())
        } returns flow
        val page = 1

        // When
        useCase = GetObjectsListUseCase(repository)
        useCase.execute(page)

        // Then
        coVerify {
            repository.getObjects(page)
        }
    }

    @Test
    fun whenUseCaseIsExecutedUseCaseReturnsObjectsList() = runTest {
        // Given
        val flow = flow {
            emit(getObjectsList())
        }
        coEvery {
            repository.getObjects(any())
        } returns flow
        val page = 1

        // When
        useCase = GetObjectsListUseCase(repository)

        // Then
        useCase.execute(page).test {
            assertEquals(getObjectsList(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
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
