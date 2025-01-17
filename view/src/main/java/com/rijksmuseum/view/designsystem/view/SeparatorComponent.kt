package com.rijksmuseum.view.designsystem.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun SeparatorComponent(
    modifier: Modifier = Modifier
) {
    Divider(
        modifier = modifier
            .fillMaxWidth(),
        color = RijksmuseumTheme.colorScheme.onBackground,
        thickness = 1.dp
    )
}
