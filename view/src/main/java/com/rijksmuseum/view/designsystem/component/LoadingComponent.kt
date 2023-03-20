package com.rijksmuseum.view.designsystem.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun LoadingComponent() {
    Dialog(
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        onDismissRequest = { /* do nothing */ }
    ) {
        Surface(
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(24.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4
)
fun FullScreenLoaderPreview() {
    RijksmuseumTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LoadingComponent()
        }
    }
}
