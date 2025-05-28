package stepan.gorokhov.viboranet.tests.presentation.createTest

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute

fun NavGraphBuilder.createTest(navController: NavController) {
    composable(TestsRoute.CreateTest.route) {
        CreateTestScreen(navController, testId = null)
    }
}
