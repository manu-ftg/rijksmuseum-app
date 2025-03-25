package com.rijksmuseum.domain.usecase

import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.repository.RijksmuseumRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

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
        val result = Result.success(getObjectDetailsModel())
        coEvery {
            repository.getObjectDetails(any())
        } returns result
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
        val result = Result.success(getObjectDetailsModel())
        coEvery {
            repository.getObjectDetails(any())
        } returns result
        val id = "id"

        // When
        useCase = GetObjectDetailsUseCase(repository)

        // Then
        useCase.execute(id).also {
            assert(it.isSuccess)
            Assert.assertEquals(Result.success(getObjectDetailsModel()), it)
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
