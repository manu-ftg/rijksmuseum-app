package com.rijksmuseum.view.designsystem.component.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rijksmuseum.presentation.display.ObjectItemDisplay
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun HeaderItemComponent(
    item: ObjectItemDisplay.HeaderItem, isSeparatorVisible: Boolean = true
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isSeparatorVisible) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.secondaryVariant)
            )
        }

        Text(
            text = item.artist,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderItemPreview() {
    RijksmuseumTheme {
        HeaderItemComponent(
            ObjectItemDisplay.HeaderItem("Vincent Van Gogh")
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeaderItemDarkPreview() {
    RijksmuseumTheme {
        HeaderItemComponent(
            ObjectItemDisplay.HeaderItem("Vincent Van Gogh")
        )
    }
}
