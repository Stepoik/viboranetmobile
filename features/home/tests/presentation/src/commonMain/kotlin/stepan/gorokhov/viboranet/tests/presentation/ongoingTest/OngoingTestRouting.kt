package stepan.gorokhov.viboranet.tests.presentation.ongoingTest

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute

internal fun NavGraphBuilder.ongoingTest(navController: NavController) {
    composable(TestsRoute.OngoingTest.route) {
        OngoingTestScreen(navController)
    }
}

internal fun NavController.navigateOngoingTest(id: String) {
    navigate("${TestsRoute.OngoingTest.navRoute}/$id")
}