package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute

fun NavGraphBuilder.mainRouting(navController: NavController) {
    composable(TestsRoute.Main.route) {
        MainTestsScreen(navController)
    }
}