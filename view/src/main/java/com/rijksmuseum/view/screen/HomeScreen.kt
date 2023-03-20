package com.rijksmuseum.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rijksmuseum.view.theme.RijksmuseumTheme

@Composable
fun HomeScreen(
    navigateToDetailScreen: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen", fontSize = 24.sp)

        Spacer(modifier = Modifier.size(24.dp))

        Text(text = "This is a button to navigate to detail screen")
        
        Button(onClick = {
            navigateToDetailScreen("id test")
        }) {
            Text(text = "Navigate to detail screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RijksmuseumTheme {
        HomeScreen {}
    }
}
