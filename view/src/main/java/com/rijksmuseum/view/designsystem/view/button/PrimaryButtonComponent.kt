package com.rijksmuseum.view.designsystem.view.button

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rijksmuseum.view.util.RijksmuseumPreview

@Composable
fun PrimaryButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrimaryButtonPreview() {
    RijksmuseumPreview {
        PrimaryButtonComponent(
            modifier = Modifier.fillMaxWidth(),
            text = "This is a button",
            onClick = {}
        )
    }
}
