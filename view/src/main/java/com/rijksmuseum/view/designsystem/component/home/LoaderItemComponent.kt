package com.rijksmuseum.view.designsystem.component.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rijksmuseum.presentation.display.ObjectItemDisplay
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun LoaderItemComponent() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp).size(24.dp).align(Alignment.Center),
            strokeWidth = 2.dp,
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoaderItemPreview() {
    RijksmuseumTheme {
        LoaderItemComponent()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoaderItemDarkPreview() {
    RijksmuseumTheme {
        LoaderItemComponent()
    }
}
