package com.rijksmuseum.view.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rijksmuseum.view.designsystem.view.button.PrimaryButtonComponent

@Composable
fun ErrorScreenComponent(
    message: String,
    buttonText: String,
    onButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Filled.ErrorOutline,
                contentDescription = "Error icon")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message)
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButtonComponent(text = buttonText) {
                onButtonClicked()
            }
        }
    }
}
