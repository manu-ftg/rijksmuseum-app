package com.rijksmuseum.view.designsystem.view.topbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rijksmuseum.view.R
import com.rijksmuseum.view.designsystem.theme.RijksmuseumTheme
import com.rijksmuseum.view.navigation.AppNavigation
import com.rijksmuseum.view.navigation.RijksmuseumNavHost

@Composable
fun TopBarComponent(
    navController: NavHostController
) {
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.top_bar_title))
        },
        navigationIcon = {
            if (currentRoute.value?.destination?.route != AppNavigation.Home.route) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigate back")
                }
            }
        },
    )
}

@Composable
@Preview(
    device = Devices.PIXEL_4
)
fun TopBarPreview() {
    val navController: NavHostController = rememberNavController()

    RijksmuseumTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = { TopBarComponent(navController) }) { paddingValues ->
            RijksmuseumNavHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    }
}
