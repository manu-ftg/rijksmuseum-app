package com.rijksmuseum.view.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme

@Composable
fun LoadingScreenComponent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(RijksmuseumTheme.spacing.x6),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(RijksmuseumTheme.spacing.x6),
                strokeWidth = 2.dp,
                color = RijksmuseumTheme.colorScheme.primary
            )
        }
    }
}
