package com.rijksmuseum.view.util

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Preview(
    name = "Light mode",
    showBackground = true
)
@Preview(
    name = "Dark mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class LightAndDarkPreviews

@Composable
fun RijksmuseumPreview(
    content: @Composable () -> Unit
) {
    RijksmuseumTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}
