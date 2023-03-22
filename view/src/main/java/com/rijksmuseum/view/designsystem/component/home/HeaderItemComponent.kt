package com.rijksmuseum.view.designsystem.component.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import com.rijksmuseum.view.designsystem.view.SeparatorComponent

@Composable
fun HeaderItemComponent(
    item: ObjectItemViewData.HeaderItem, isSeparatorVisible: Boolean = true
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isSeparatorVisible) {
            SeparatorComponent()
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
        Surface {
            HeaderItemComponent(
                ObjectItemViewData.HeaderItem("Vincent Van Gogh")
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeaderItemDarkPreview() {
    RijksmuseumTheme {
        Surface {
            HeaderItemComponent(
                ObjectItemViewData.HeaderItem("Vincent Van Gogh")
            )
        }
    }
}
