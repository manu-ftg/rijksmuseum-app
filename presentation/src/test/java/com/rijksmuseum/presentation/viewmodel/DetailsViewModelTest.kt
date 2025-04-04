package com.rijksmuseum.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.rijksmuseum.domain.model.ObjectDetailsModel
import com.rijksmuseum.domain.usecase.GetObjectDetailsUseCase
import com.rijksmuseum.presentation.util.DispatcherProvider
import com.rijksmuseum.presentation.util.TestDispatcherProvider
import com.rijksmuseum.presentation.viewdata.ObjectViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    private lateinit var getObjectDetailsUseCase: GetObjectDetailsUseCase

    private lateinit var viewModel: DetailsViewModel

    private lateinit var dispatcher: DispatcherProvider

    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        dispatcher = TestDispatcherProvider()

        getObjectDetailsUseCase = mockk()
        savedStateHandle = mockk()
    }

    @Test
    fun whenStartViewModelAndObjectIsLoaded() = runTest {
        // Given
        val result = Result.success(
            getObjectDetailsModel()
        )
        coEvery {
            getObjectDetailsUseCase.execute(any())
        } returns result

        every {
            savedStateHandle.get<String>(any())
        } returns "id"

        // When
        viewModel = DetailsViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcher,
            getObjectDetailsUseCase = getObjectDetailsUseCase
        )

        // Then
        viewModel.state.test {
            assertEquals(ScreenState.Loaded(content = getObjectViewData()), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenStartViewModelUseCaseIsCalled() = runTest {
        // Given
        val result = Result.success(
            getObjectDetailsModel()
        )
        coEvery {
            getObjectDetailsUseCase.execute(any())
        } returns result

        every {
            savedStateHandle.get<String>(any())
        } returns "id"

        // When
        viewModel = DetailsViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcher,
            getObjectDetailsUseCase = getObjectDetailsUseCase
        )

        // Then
        coVerify {
            getObjectDetailsUseCase.execute(any())
        }
    }

    @Test
    fun whenStartViewModelAndLoadObjectFailsShowError() = runTest {
        // Given
        val errorResult = Result.failure<ObjectDetailsModel>(Throwable())
        coEvery {
            getObjectDetailsUseCase.execute(any())
        } returns errorResult

        every {
            savedStateHandle.get<String>(any())
        } returns "id"

        // When
        viewModel = DetailsViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcher,
            getObjectDetailsUseCase = getObjectDetailsUseCase
        )

        // Then
        viewModel.state.test {
            assertEquals(ScreenState.Error(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenErrorMessageIsClickedNavigateBack() = runTest {
        // Given
        val errorResult = Result.failure<ObjectDetailsModel>(Throwable())
        coEvery {
            getObjectDetailsUseCase.execute(any())
        } returns errorResult

        every {
            savedStateHandle.get<String>(any())
        } returns "id"

        // When
        viewModel = DetailsViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcher,
            getObjectDetailsUseCase = getObjectDetailsUseCase
        )

        // Then
        viewModel.events.test {
            viewModel.onDialogClicked()

            assertEquals(DetailsEvent.NavigateBack, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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

    private fun getObjectViewData() = ObjectViewData(
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
