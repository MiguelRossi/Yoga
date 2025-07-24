package com.example.yoga.main_ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.yoga.asana_detail.ui.DetailScreen
import com.example.yoga.asanas.ui.AsanasScreen

@Composable
fun MainNavigationHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Asanas
    ) {
        composable<Asanas> {
            AsanasScreen(modifier = modifier) { poseId, poseName ->
                navController.navigate(route = Detail(poseId, poseName))
            }
        }
        composable<Detail> { backStackEntry ->
            val route: Detail = backStackEntry.toRoute()
            DetailScreen(
                modifier = modifier,
                poseId = route.poseId,
            )
        }
    }
}
