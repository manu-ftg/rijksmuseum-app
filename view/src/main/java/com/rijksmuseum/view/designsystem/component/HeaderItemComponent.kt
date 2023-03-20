package com.rijksmuseum.view.designsystem.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rijksmuseum.presentation.display.ObjectItemDisplay

@Composable
fun HeaderItemComponent(
    item: ObjectItemDisplay.HeaderItem
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(MaterialTheme.colors.onBackground))

        Text(text = item.artist, fontSize = 24.sp, modifier = Modifier.padding(24.dp))
    }
}
