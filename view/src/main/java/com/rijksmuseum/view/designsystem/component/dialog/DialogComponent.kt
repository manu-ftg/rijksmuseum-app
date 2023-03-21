package com.rijksmuseum.view.designsystem.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rijksmuseum.view.designsystem.component.button.PrimaryButtonComponent
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun DialogComponent(
    title: String,
    subtitle: String? = null,
    buttonText: String,
    onClick: () -> Unit
) {
    Dialog(
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        onDismissRequest = { /* do nothing */ }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                )
                subtitle?.let {
                    Text(
                        text = subtitle,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                PrimaryButtonComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = buttonText,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4
)
fun DialogPreview() {
    RijksmuseumTheme {
        DialogComponent(
            title = "This is a title",
            subtitle = "And this is a subtitle",
            buttonText = "Ok",
            onClick = {}
        )
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4
)
fun DialogNoSubtitlePreview() {
    RijksmuseumTheme {
        DialogComponent(
            title = "This is a title",
            buttonText = "Retry",
            onClick = {}
        )
    }
}
