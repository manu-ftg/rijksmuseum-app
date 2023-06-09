package com.rijksmuseum.presentation.viewmodel

import app.cash.turbine.test
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.usecase.GetObjectsListUseCase
import com.rijksmuseum.presentation.util.DispatcherProvider
import com.rijksmuseum.presentation.util.TestDispatcherProvider
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var getObjectsListUseCase: GetObjectsListUseCase

    private lateinit var viewModel: HomeViewModel

    private lateinit var dispatcher: DispatcherProvider

    @Before
    fun setUp() {
        dispatcher = TestDispatcherProvider()

        getObjectsListUseCase = mockk()
    }

    @Test
    fun whenStartViewModelAndObjectListIsLoaded() = runTest {
        // Given
        val flow = flow {
            emit(getObjectsList())
        }
        coEvery {
            getObjectsListUseCase.execute(any())
        } returns flow
        val objectsViewDataList = getObjectsViewDataList()

        // When
        viewModel = HomeViewModel(
            getObjectsListUseCase,
            dispatcher
        )

        // Then
        viewModel.state.test {
            assertEquals(ScreenState.Loaded(content = HomeState(objectsList = objectsViewDataList)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenStartViewModelAndErrorIsReceived() = runTest {
        // Given
        val flow = flow<List<ObjectModel>> {
            throw Throwable()
        }
        coEvery {
            getObjectsListUseCase.execute(any())
        } returns flow

        // When
        viewModel = HomeViewModel(
            getObjectsListUseCase,
            dispatcher
        )

        // Then
        viewModel.state.test {
            assertEquals(ScreenState.Error(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenMoreItemsAreLoaded() = runTest {
        // Given
        val flow = flow {
            emit(getObjectsList())
        }
        coEvery {
            getObjectsListUseCase.execute(any())
        } returns flow
        val objectsViewDataList = getObjectsViewDataList()

        // When
        viewModel = HomeViewModel(
            getObjectsListUseCase,
            dispatcher
        )

        // Then
        viewModel.state.test {
            viewModel.onLoadingItemReached()

            assertEquals(ScreenState.Loaded(content = HomeState(objectsList = objectsViewDataList)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenItemIsClickedNavigateToDetailScreen() = runTest {
        // Given
        val flow = flow {
            emit(getObjectsList())
        }
        coEvery {
            getObjectsListUseCase.execute(any())
        } returns flow

        // When
        viewModel = HomeViewModel(
            getObjectsListUseCase,
            dispatcher
        )

        // Then
        viewModel.events.test {
            viewModel.onObjectClicked("itemId")

            assertEquals(HomeEvent.NavigateToDetail("itemId"), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenLoadMoreItemsFailsShowErrorDialog() = runTest {
        // Given
        val flow = flow {
            emit(getObjectsList())
        }
        val errorFlow = flow<List<ObjectModel>> {
            throw Throwable()
        }
        coEvery {
            getObjectsListUseCase.execute(1)
        } returns flow
        coEvery {
            getObjectsListUseCase.execute(2)
        } returns errorFlow
        val objectsViewDataList = getObjectsViewDataList()

        // When
        viewModel = HomeViewModel(
            getObjectsListUseCase,
            dispatcher
        )

        // Then
        viewModel.state.test {
            viewModel.onLoadingItemReached()

            assertEquals(ScreenState.Loaded(content = HomeState(objectsList = objectsViewDataList)), awaitItem())
            assertEquals(ScreenState.Loaded(content = HomeState(showError = true, objectsList = objectsViewDataList)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun getObjectsList() = listOf(
        ObjectModel("id1", "url", "number1", "title", "artistA"),
        ObjectModel("id2", "url","number2", "title", "artistA"),
        ObjectModel("id3", "url", "number3", "title", "artistB")
    )

    private fun getObjectsViewDataList(): List<ObjectItemViewData> = listOf(
        ObjectItemViewData.HeaderItem("artistA", "id1"),
        ObjectItemViewData.ObjectItem("id1", "title", "artistA", "number1", "url"),
        ObjectItemViewData.ObjectItem("id2", "title", "artistA", "number2", "url"),
        ObjectItemViewData.HeaderItem("artistB", "id3"),
        ObjectItemViewData.ObjectItem("id3", "title", "artistB", "number3", "url"),
        ObjectItemViewData.LoaderItem
    )
}
