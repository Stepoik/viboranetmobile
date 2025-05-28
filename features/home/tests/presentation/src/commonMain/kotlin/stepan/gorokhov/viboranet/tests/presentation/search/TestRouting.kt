package stepan.gorokhov.viboranet.tests.presentation.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute

fun NavGraphBuilder.search(navController: NavController) {
    composable(TestsRoute.Search.route) {
        SearchScreen(navController)
    }
}