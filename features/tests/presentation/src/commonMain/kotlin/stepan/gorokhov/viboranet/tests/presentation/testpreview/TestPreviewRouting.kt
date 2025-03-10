package stepan.gorokhov.viboranet.tests.presentation.testpreview

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute

fun NavGraphBuilder.testPreview(navController: NavController) {
    composable(TestsRoute.TestPreview.route) {
        TestPreviewScreen(navController = navController)
    }
}

fun NavController.navigateTestPreview(id: String) {
    navigate("${TestsRoute.TestPreview.navRoute}/$id")
}