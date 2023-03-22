package com.rijksmuseum.view.designsystem.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun SeparatorComponent(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier
        .fillMaxWidth()
        .alpha(0.7f)
        .height(1.dp)
        .background(MaterialTheme.colors.onBackground)
    )
}
