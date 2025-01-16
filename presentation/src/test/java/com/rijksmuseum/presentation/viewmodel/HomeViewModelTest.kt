package com.rijksmuseum.presentation.viewmodel

import app.cash.turbine.test
import com.rijksmuseum.domain.model.ObjectModel
import com.rijksmuseum.domain.model.PageDataModel
import com.rijksmuseum.domain.usecase.GetObjectsListPageUseCase
import com.rijksmuseum.presentation.util.DispatcherProvider
import com.rijksmuseum.presentation.util.TestDispatcherProvider
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import io.mockk.coEvery
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
class HomeViewModelTest {

    private lateinit var getObjectsListPageUseCase: GetObjectsListPageUseCase

    private lateinit var viewModel: HomeViewModel

    private lateinit var dispatcher: DispatcherProvider

    @Before
    fun setUp() {
        getObjectsListPageUseCase = mockk()
        dispatcher = TestDispatcherProvider()
    }

    @Test
    fun whenStartViewModelAndObjectListIsLoaded() = runTest {
        // Given
        val model = PageDataModel.NewData(
            1,
            getObjectsList()
        )
        coEvery {
            getObjectsListPageUseCase.execute(any())
        } returns model
        val objectsViewDataList = getObjectsViewDataList()

        // When
        viewModel = HomeViewModel(
            getObjectsListPageUseCase,
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
        val errorModel = PageDataModel.Error(Throwable())
        coEvery {
            getObjectsListPageUseCase.execute(any())
        } returns errorModel

        // When
        viewModel = HomeViewModel(
            getObjectsListPageUseCase,
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
        val model = PageDataModel.NewData(
            1,
            getObjectsList()
        )
        coEvery {
            getObjectsListPageUseCase.execute(any())
        } returns model
        val objectsViewDataList = getObjectsViewDataList()

        // When
        viewModel = HomeViewModel(
            getObjectsListPageUseCase,
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
        val model = PageDataModel.NewData(
            1,
            getObjectsList()
        )
        coEvery {
            getObjectsListPageUseCase.execute(any())
        } returns model

        // When
        viewModel = HomeViewModel(
            getObjectsListPageUseCase,
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
        val model = PageDataModel.NewData(
            1,
            getObjectsList()
        )
        val errorModel = PageDataModel.Error(Throwable())

        coEvery {
            getObjectsListPageUseCase.execute(null)
        } returns model
        coEvery {
            getObjectsListPageUseCase.execute(2)
        } returns errorModel
        val objectsViewDataList = getObjectsViewDataList()

        // When
        viewModel = HomeViewModel(
            getObjectsListPageUseCase,
            dispatcher
        )

        // Then
        viewModel.state.test {
            viewModel.onLoadingItemReached()

            assertEquals(ScreenState.Loaded(content = HomeState(objectsList = objectsViewDataList)), awaitItem())
            assertEquals(ScreenState.Loaded(content = HomeState(isLoadingMore  = true, objectsList = objectsViewDataList)), awaitItem())
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
        ObjectItemViewData.HeaderItem("artistA"),
        ObjectItemViewData.ObjectItem("id1", "title", "artistA", "number1", "url"),
        ObjectItemViewData.ObjectItem("id2", "title", "artistA", "number2", "url"),
        ObjectItemViewData.HeaderItem("artistB"),
        ObjectItemViewData.ObjectItem("id3", "title", "artistB", "number3", "url"),
        ObjectItemViewData.LoaderItem
    )
}
