package stepan.gorokhov.viboranet.home.flow

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.home.route.HomeRoute
import stepan.gorokhov.viboranet.tests.presentation.tests

fun NavGraphBuilder.home(parentNavController: NavController) {
    composable(ApplicationRoute.Home.route) {
        val navController = rememberNavController()

        NavHost(navController, startDestination = HomeRoute.Tests.route) {
            tests(navController)
        }
    }
}