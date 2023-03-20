package com.rijksmuseum.view.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rijksmuseum.presentation.display.ObjectItemDisplay
import com.rijksmuseum.view.R

@Composable
fun ObjectItemComponent(
    item: ObjectItemDisplay.ObjectItem,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(1.dp).background(MaterialTheme.colors.onBackground))

        Row(
            modifier = Modifier
                .clickable {
                    onClick(item.id)
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = item.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_placeholder),)

            Column(modifier = Modifier.weight(1f, true)) {
                Text(text = item.title, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 16.dp))
                Text(text = item.artist, fontSize = 12.sp, modifier = Modifier.padding(start = 24.dp, end = 16.dp))
                Text(text = item.objectNumber, fontSize = 8.sp, modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.End))
            }
        }
    }
}
