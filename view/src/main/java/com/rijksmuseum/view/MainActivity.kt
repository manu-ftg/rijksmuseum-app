package com.rijksmuseum.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rijksmuseum.view.theme.RijksmuseumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RijksmuseumTheme {
                Content("World")
            }
        }
    }
}

@Composable
fun Content(name: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(modifier = Modifier.align(Alignment.Center), text = "Hello $name!")
        }
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4
)
fun MainActivityLightPreview() {
    RijksmuseumTheme {
        Content("World")
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun MainActivityDarkPreview() {
    RijksmuseumTheme {
        Content("World")
    }
}
