package com.rijksmuseum.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rijksmuseum.presentation.display.ObjectItemDisplay
import com.rijksmuseum.presentation.viewmodel.HomeState
import com.rijksmuseum.presentation.viewmodel.HomeViewModel
import com.rijksmuseum.view.designsystem.component.LoadingComponent
import com.rijksmuseum.view.designsystem.component.home.HeaderItemComponent
import com.rijksmuseum.view.designsystem.component.home.LoaderItemComponent
import com.rijksmuseum.view.designsystem.component.home.ObjectItemComponent
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit,
) {
    val state: HomeState by viewModel.state.collectAsState()

    HomeContent(state, navigateToDetailScreen) { viewModel.onLastItemReached() }
}

@Composable
fun HomeContent(
    state: HomeState,
    navigateToDetailScreen: (String) -> Unit,
    onLoadingItemReached: () -> Unit
) {
    Box {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(
                items = state.objectsList,
                key = { _, item -> item.key }
            ) { index, item ->
                when (item) {
                    is ObjectItemDisplay.HeaderItem -> {
                        HeaderItemComponent(
                            item = item,
                            isSeparatorVisible = index != 0
                        )
                    }
                    ObjectItemDisplay.LoaderItem -> {
                        LoaderItemComponent()
                    }
                    is ObjectItemDisplay.ObjectItem -> {
                        ObjectItemComponent(
                            item = item,
                            onClick =  {
                                navigateToDetailScreen(item.objectNumber)
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

        if (state.isLoading) {
            LoadingComponent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    RijksmuseumTheme {
        HomeContent(HomeState(), {}) {}
    }
}

@Preview(showBackground = true)
@Composable
fun HomeIsLoadingPreview() {
    RijksmuseumTheme {
        HomeContent(HomeState(isLoading = true), {}) {}
    }
}
