package com.rijksmuseum.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import com.rijksmuseum.view.designsystem.view.topbar.TopBarComponent
import com.rijksmuseum.view.navigation.RijksmuseumNavHost
import com.rijksmuseum.view.navigation.isBackButtonVisible

@Composable
fun RijksmuseumApp(
    navController: NavHostController = rememberNavController(),
) {
    val barColor = RijksmuseumTheme.colorScheme.primaryVariant
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(systemUiController, barColor) {
        systemUiController.setStatusBarColor(
            color = barColor
        )
    }

    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
        .value

    Scaffold(
        topBar = {
            TopBarComponent(
                isBackButtonVisible = currentRoute.isBackButtonVisible(),
                onBackButtonClicked = {
                    navController.navigateUp()
                }
            )
        }
    ) { paddingValues ->
        RijksmuseumNavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}
