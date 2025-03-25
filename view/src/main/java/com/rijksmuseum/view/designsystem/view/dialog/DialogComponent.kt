package com.rijksmuseum.view.designsystem.view.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import com.rijksmuseum.view.designsystem.view.button.PrimaryButtonComponent

@Composable
fun DialogComponent(
    isVisible: Boolean = true,
    title: String,
    subtitle: String? = null,
    firstButtonText: String,
    onClickFirst: () -> Unit,
    secondButtonText: String? = null,
    onClickSecond: () -> Unit = {}
) {
    if (isVisible) {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
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
                        style = RijksmuseumTheme.typography.h6
                    )
                    subtitle?.let {
                        Text(
                            text = subtitle,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = RijksmuseumTheme.typography.body1
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        PrimaryButtonComponent(
                            modifier = Modifier.weight(1f),
                            text = firstButtonText,
                            onClick = onClickFirst
                        )
                        if (secondButtonText != null) {
                            PrimaryButtonComponent(
                                modifier = Modifier.weight(1f),
                                text = secondButtonText,
                                onClick = onClickSecond
                            )
                        }
                    }
                }
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
            firstButtonText = "Ok",
            onClickFirst = {}
        )
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4
)
fun DialogTwoButtonsPreview() {
    RijksmuseumTheme {
        DialogComponent(
            title = "This is a title",
            subtitle = "And this is a subtitle",
            firstButtonText = "Ok",
            onClickFirst = {},
            secondButtonText = "Cancel",
            onClickSecond = {}
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
            firstButtonText = "Retry",
            onClickFirst = {}
        )
    }
}
