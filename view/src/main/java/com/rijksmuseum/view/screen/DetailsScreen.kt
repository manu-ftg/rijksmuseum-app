package com.rijksmuseum.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.rijksmuseum.presentation.viewdata.ObjectViewData
import com.rijksmuseum.presentation.viewdata.ScreenState
import com.rijksmuseum.presentation.viewmodel.DetailsEvent
import com.rijksmuseum.presentation.viewmodel.DetailsViewModel
import com.rijksmuseum.view.R
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import com.rijksmuseum.view.designsystem.view.dialog.DialogComponent

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val state: ScreenState<ObjectViewData> by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                is DetailsEvent.NavigateBack -> navigateBack()
            }
        }
    }

    DetailsContent(
        state = state,
        onDialogClicked = viewModel::onDialogClicked
    )
}

@Composable
fun DetailsContent(
    state: ScreenState<ObjectViewData>,
    onDialogClicked: () -> Unit = {}
) {
    when (state) {
        is ScreenState.Error -> {
            DialogComponent(
                title = stringResource(R.string.common_error_title),
                subtitle = state.message ?: stringResource(R.string.common_error_message),
                firstButtonText = stringResource(R.string.common_ok_text),
                onClickFirst = onDialogClicked
            )
        }
        is ScreenState.Loaded -> ObjectDetailsContent(state.content)
        ScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(24.dp),
                    strokeWidth = 2.dp,
                    color = RijksmuseumTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun ObjectDetailsContent(
    details: ObjectViewData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(Color(0xFFC4C4C4)),
            model = details.imageUrl,
            contentScale = ContentScale.Fit,
            contentDescription = details.imageUrl,
            loading = {
                Box(
                    modifier = Modifier.background(Color(0xFFC4C4C4)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        )

        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text(
                text = details.title,
                style = RijksmuseumTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.author_title, details.artist),
                style = RijksmuseumTheme.typography.subtitle1
            )

            details.physicalMedium?.let { physicalMedium ->
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.physical_medium_title, physicalMedium),
                    style = RijksmuseumTheme.typography.subtitle2
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            details.description?.let { _description ->
                Text(
                    text = _description,
                    style = RijksmuseumTheme.typography.body1
                )
            }

            if (details.documentation.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Documentation:",
                    style = RijksmuseumTheme.typography.body2
                )

                details.documentation.forEach { documentation ->
                    Text(
                        text = documentation,
                        style = RijksmuseumTheme.typography.caption,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4)
@Composable
fun DetailsScreenErrorPreview() {
    RijksmuseumTheme {
        DetailsContent(ScreenState.Error())
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4)
@Composable
fun DetailsLoadedPreview() {
    RijksmuseumTheme {
        DetailsContent(ScreenState.Loaded(ObjectViewData(
            "id",
            "imageUrl",
            "This is the title",
        "subtitle",
        "artist",
        "This is a not really long description",
        listOf("documentation1", "documentation2"),
        "physicalMedium"
        )))
    }
}
