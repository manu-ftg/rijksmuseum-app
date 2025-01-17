package com.rijksmuseum.view.designsystem.component.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import com.rijksmuseum.view.designsystem.view.SeparatorComponent
import com.rijksmuseum.view.util.LightAndDarkPreviews
import com.rijksmuseum.view.util.RijksmuseumPreview

@Composable
fun HeaderItemComponent(
    item: ObjectItemViewData.HeaderItem,
    isSeparatorVisible: Boolean = true
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isSeparatorVisible) {
            SeparatorComponent(
                modifier = Modifier
                    .padding(horizontal = RijksmuseumTheme.spacing.x4)
                    .alpha(0.1f)
            )

            Spacer(modifier = Modifier.height(RijksmuseumTheme.spacing.x4))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(RijksmuseumTheme.spacing.x8),
                imageVector = Icons.Filled.Person,
                contentDescription = "Author icon")

            Spacer(modifier = Modifier.width(RijksmuseumTheme.spacing.x2))

            Text(
                modifier = Modifier
                    .weight(1f),
                text = item.artist,
                style = RijksmuseumTheme.typography.h6
            )
        }
    }
}

@LightAndDarkPreviews
@Composable
fun HeaderItemPreview() {
    RijksmuseumPreview {
        HeaderItemComponent(
            ObjectItemViewData.HeaderItem("Vincent Van Gogh")
        )
    }
}
