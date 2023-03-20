package com.rijksmuseum.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rijksmuseum.view.theme.RijksmuseumTheme

@Composable
fun DetailsScreen(objectId: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Details Screen $objectId", fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    RijksmuseumTheme {
        DetailsScreen("ABC1234")
    }
}