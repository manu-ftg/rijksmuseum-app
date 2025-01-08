package com.rijksmuseum.view.designsystem.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.view.R
import com.rijksmuseum.view.util.LightAndDarkPreviews
import com.rijksmuseum.view.util.RijksmuseumPreview

@Composable
fun ObjectItemComponent(
    modifier: Modifier = Modifier,
    item: ObjectItemViewData.ObjectItem,
    onClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = MaterialTheme.shapes.large)
            .clickable {
                onClick(item.objectNumber)
            }
            .background(color = MaterialTheme.colors.surface, shape = MaterialTheme.shapes.large)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.size(64.dp),
            model = item.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            loading = {
                Box(
                    modifier = Modifier.background(Color(0xFFC4C4C4)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                }
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        )

        Column(modifier = Modifier.weight(1f, true)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = stringResource(R.string.author_title, item.artist),
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = item.objectNumber,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Chevron")
    }
}

@LightAndDarkPreviews
@Composable
fun ObjectItemPreview() {
    RijksmuseumPreview {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ObjectItemComponent(
                item = ObjectItemViewData.ObjectItem(
                    "Id1",
                    "This is the title",
                    "This is the artist",
                    "1243234243"
                ),
                onClick = {}
            )

            ObjectItemComponent(
                item = ObjectItemViewData.ObjectItem(
                    "Id2",
                    "This is the title",
                    "This is the artist",
                    "34344234"
                ),
                onClick = {}
            )

            ObjectItemComponent(
                item = ObjectItemViewData.ObjectItem(
                    "Id3",
                    "This is the title",
                    "This is the artist",
                    "7687856"
                ),
                onClick = {}
            )
        }
    }
}
