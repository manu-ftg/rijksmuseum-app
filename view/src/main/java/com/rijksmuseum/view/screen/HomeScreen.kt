package com.rijksmuseum.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import com.rijksmuseum.presentation.viewmodel.HomeEvent
import com.rijksmuseum.presentation.viewmodel.HomeState
import com.rijksmuseum.presentation.viewmodel.HomeViewModel
import com.rijksmuseum.view.R
import com.rijksmuseum.view.designsystem.component.ErrorScreenComponent
import com.rijksmuseum.view.designsystem.component.LoadingScreenComponent
import com.rijksmuseum.view.designsystem.component.home.HeaderItemComponent
import com.rijksmuseum.view.designsystem.component.home.LoaderItemComponent
import com.rijksmuseum.view.designsystem.component.home.ObjectItemComponent
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import com.rijksmuseum.view.designsystem.view.dialog.DialogComponent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit,
) {
    val state: ScreenState<HomeState> by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                is HomeEvent.NavigateToDetail -> navigateToDetailScreen(event.objectNumber)
            }
        }
    }

    HomeContent(
        state = state,
        onItemClicked = viewModel::onObjectClicked,
        onLoadingItemReached = viewModel::onLoadingItemReached,
        onRetryClicked = viewModel::onRetryClicked,
        onDialogDismissed = viewModel::onDialogDismissed
    )
}

@Composable
fun HomeContent(
    state: ScreenState<HomeState>,
    onItemClicked: (String) -> Unit,
    onLoadingItemReached: () -> Unit,
    onRetryClicked: () -> Unit,
    onDialogDismissed: () -> Unit
) {
    when (state) {
        is ScreenState.Error -> ErrorScreenComponent(
            message = stringResource(R.string.common_error_message),
            buttonText = stringResource(R.string.retry_button_text),
            onButtonClicked = onRetryClicked
        )
        is ScreenState.Loaded -> HomeLoadedContent(
            state = state.content,
            onItemClicked = onItemClicked,
            onLoadingItemReached = onLoadingItemReached,
            onRetryClicked = onRetryClicked,
            onDialogDismissed = onDialogDismissed
        )
        ScreenState.Loading -> LoadingScreenComponent()
    }
}

@Composable
fun HomeLoadedContent(
    state: HomeState,
    onItemClicked: (String) -> Unit,
    onLoadingItemReached: () -> Unit,
    onRetryClicked: () -> Unit,
    onDialogDismissed: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                items = state.objectsList
            ) { index, item ->
                when (item) {
                    is ObjectItemViewData.HeaderItem -> {
                        HeaderItemComponent(
                            item = item,
                            isSeparatorVisible = index != 0
                        )
                    }
                    ObjectItemViewData.LoaderItem -> {
                        LoaderItemComponent()
                    }
                    is ObjectItemViewData.ObjectItem -> {
                        ObjectItemComponent(
                            item = item,
                            onClick =  { objectNumber ->
                                onItemClicked(objectNumber)
                            }
                        )
                    }
                }

                if (index == state.objectsList.size - 2) {
                    LaunchedEffect(index) {
                        onLoadingItemReached()
                    }
                }
            }
        }

        DialogComponent(
            isVisible = state.showError,
            title = stringResource(R.string.common_error_title),
            subtitle = stringResource(R.string.pagination_error_message),
            firstButtonText = stringResource(R.string.common_ok_text),
            onClickFirst = onDialogDismissed,
            secondButtonText = stringResource(R.string.retry_button_text),
            onClickSecond = onRetryClicked)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    RijksmuseumTheme {
        HomeContent(
            state = ScreenState.Loaded(
                HomeState(
                    objectsList = listOf<ObjectItemViewData>(
                        ObjectItemViewData.HeaderItem("Artist"),
                        ObjectItemViewData.ObjectItem("id1", "Title", "Artist", "abc"),
                        ObjectItemViewData.HeaderItem("Artist 2"),
                        ObjectItemViewData.ObjectItem("id2", "Title", "Artist 2", "abc1"),
                        ObjectItemViewData.ObjectItem("id3", "Title", "Artist 2", "abc2"),
                        ObjectItemViewData.ObjectItem("id4", "Title", "Artist 2", "abc3"),
                        ObjectItemViewData.ObjectItem("id5", "Title", "Artist 2", "abc4"),
                        ObjectItemViewData.LoaderItem
                    )
                )
            ),
            onItemClicked = {},
            onLoadingItemReached = {},
            onRetryClicked = {},
            onDialogDismissed = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeIsLoadingPreview() {
    RijksmuseumTheme {
        HomeContent(
            state = ScreenState.Loading,
            onItemClicked = {},
            onLoadingItemReached = {},
            onRetryClicked = {},
            onDialogDismissed = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeErrorPreview() {
    RijksmuseumTheme {
        HomeContent(
            state = ScreenState.Error(),
            onItemClicked = {},
            onLoadingItemReached = {},
            onRetryClicked = {},
            onDialogDismissed = {}
        )
    }
}
