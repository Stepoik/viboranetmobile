package stepan.gorokhov.viboranet.tests.presentation.testpreview

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute

internal fun NavGraphBuilder.testPreview(navController: NavController) {
    composable(TestsRoute.TestPreview.route) {
        TestPreviewScreen(navController = navController)
    }
}

internal fun NavController.navigateTestPreview(id: String) {
    navigate("${TestsRoute.TestPreview.navRoute}/$id")
}