package com.rijksmuseum.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rijksmuseum.view.designsystem.component.TopBarComponent
import com.rijksmuseum.view.navigation.RijksmuseumNavHost

@Composable
fun RijksmuseumApp(
    navController: NavHostController = rememberNavController(),
) {
    val barColor = MaterialTheme.colors.primary
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(systemUiController, barColor) {
        systemUiController.setStatusBarColor(
            color = barColor
        )
    }

    Scaffold(
        topBar = {
            TopBarComponent(navController)
        }
    ) { paddingValues ->
        RijksmuseumNavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}
