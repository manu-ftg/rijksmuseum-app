package com.rijksmuseum.view.designsystem.component.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

        Row(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Filled.Person,
                contentDescription = "Author icon")

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier
                    .weight(1f),
                text = item.artist,
                style = MaterialTheme.typography.h5
            )
        }
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
