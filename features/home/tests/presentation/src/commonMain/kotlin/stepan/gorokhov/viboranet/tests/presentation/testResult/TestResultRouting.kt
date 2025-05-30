package stepan.gorokhov.viboranet.tests.presentation.testResult

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.coreui.routing.popUpGraph
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute

fun NavGraphBuilder.testResult(navController: NavController) {
    composable(TestsRoute.TestResult.route) {
        TestResultScreen(navController)
    }
}

fun NavController.navigateTestResult(id: String, builder: NavOptionsBuilder.() -> Unit) {
    navigate("${TestsRoute.TestResult.navRoute}/$id", builder)
}