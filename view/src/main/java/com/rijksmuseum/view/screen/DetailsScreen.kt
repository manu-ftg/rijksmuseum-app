package com.rijksmuseum.view.screen

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rijksmuseum.presentation.display.ObjectDisplay
import com.rijksmuseum.presentation.display.ScreenState
import com.rijksmuseum.presentation.viewmodel.DetailsViewModel
import com.rijksmuseum.view.R
import com.rijksmuseum.view.designsystem.component.dialog.DialogComponent
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val state: ScreenState<ObjectDisplay> by viewModel.state.collectAsState()

    DetailsContent(state = state, navigateBack = navigateBack)
}

@Composable
fun DetailsContent(
    state: ScreenState<ObjectDisplay>,
    navigateBack: () -> Unit = {}
) {
    when (state) {
        is ScreenState.Error -> {
            DialogComponent(
                title = "Error",
                subtitle = state.message ?: "There was a problem loading the information",
                buttonText = "Ok",
                onClick = navigateBack
            )
        }
        is ScreenState.Loaded -> ObjectDetailsContent(state.content)
        ScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(24.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
fun ObjectDetailsContent(
    details: ObjectDisplay
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(Color(0xFFC4C4C4)),
            model = details.imageUrl,
            contentScale = ContentScale.Fit,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = details.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = details.artist,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        details.physicalMedium?.let { _physicalMedium ->
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = _physicalMedium,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = details.description ?: "",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4)
@Composable
fun DetailsScreenPreview() {
    RijksmuseumTheme {
        DetailsContent(ScreenState.Error())
    }
}
