package com.rijksmuseum.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RijksmuseumTheme {
                RijksmuseumApp()
            }
        }
    }
}

@Preview(
    device = Devices.PIXEL_4
)
@Preview(
    device = Devices.PIXEL_4,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainActivityPreview() {
    RijksmuseumTheme {
        RijksmuseumApp()
    }
}
