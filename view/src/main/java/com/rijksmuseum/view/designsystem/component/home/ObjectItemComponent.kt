package com.rijksmuseum.view.designsystem.component.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.rijksmuseum.presentation.viewdata.ObjectItemViewData
import com.rijksmuseum.view.R
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun ObjectItemComponent(
    item: ObjectItemViewData.ObjectItem,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(1.dp)
            .background(MaterialTheme.colors.secondaryVariant)
        )

        Row(
            modifier = Modifier
                .clickable {
                    onClick(item.objectNumber)
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
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
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = item.artist,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 24.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = item.objectNumber,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Chevron")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ObjectItemPreview() {
    RijksmuseumTheme {
        Surface {
            ObjectItemComponent(
                item = ObjectItemViewData.ObjectItem(
                    "Id",
                    "This is the title",
                    "This is the artist",
                    "1243234243"
                ),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ObjectItemDarkPreview() {
    RijksmuseumTheme {
        Surface {
            ObjectItemComponent(
                item = ObjectItemViewData.ObjectItem(
                    "Id",
                    "This is the title",
                    "This is the artist",
                    "1243234243"
                ),
                onClick = {}
            )
        }
    }
}
