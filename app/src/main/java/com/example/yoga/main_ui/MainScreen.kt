package com.example.yoga.main_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.yoga.R
import com.example.yoga.main_ui.navigation.Detail
import com.example.yoga.main_ui.navigation.MainNavigationHost

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val hasDetailRoute = backStackEntry?.destination?.hasRoute(route = Detail::class) == true

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                screenName = getTitle(hasDetailRoute, backStackEntry),
                canNavigateBack = hasDetailRoute,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(paddingValues = innerPadding)
        MainNavigationHost(
            modifier = modifier,
            navController = navController
        )
    }
}

@Composable
private fun getTitle(
    hasDetailRoute: Boolean,
    backStackEntry: NavBackStackEntry?
) = if (hasDetailRoute) {
    backStackEntry?.toRoute<Detail>()?.poseName
        ?: stringResource(id = R.string.app_name)
} else {
    stringResource(id = R.string.app_name)
}
