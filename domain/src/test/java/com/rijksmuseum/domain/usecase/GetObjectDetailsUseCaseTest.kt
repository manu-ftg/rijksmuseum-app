package com.rijksmuseum.domain.usecase

import app.cash.turbine.test
import com.rijksmuseum.domain.model.ObjectDetailsModel
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
class GetObjectDetailsUseCaseTest {

    private lateinit var repository: RijksmuseumRepository

    private lateinit var useCase: GetObjectDetailsUseCase

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun whenUseCaseIsExecutedRepositoryIsCalled() = runTest {
        // Given
        val flow = flow {
            emit(getObjectDetailsModel())
        }
        coEvery {
            repository.getObjectDetails(any())
        } returns flow
        val id = "id"

        // When
        useCase = GetObjectDetailsUseCase(repository)
        useCase.execute(id)

        // Then
        coVerify {
            repository.getObjectDetails(id)
        }
    }

    @Test
    fun whenUseCaseIsExecutedUseCaseReturnsObjectDetails() = runTest {
        // Given
        val flow = flow {
            emit(getObjectDetailsModel())
        }
        coEvery {
            repository.getObjectDetails(any())
        } returns flow
        val id = "id"

        // When
        useCase = GetObjectDetailsUseCase(repository)

        // Then
        useCase.execute(id).test {
            assertEquals(getObjectDetailsModel(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
    }

    private fun getObjectDetailsModel() = ObjectDetailsModel(
        id = "id",
        imageUrl = "url",
        title = "title",
        subtitle = "subtitle",
        artist = "artist",
        documentation = listOf(),
        description = "description",
        physicalMedium = "medium"
    )
}
