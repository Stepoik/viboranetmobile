package stepan.gorokhov.viboranet.tests.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.tests.presentation.main.mainRouting
import stepan.gorokhov.viboranet.tests.presentation.testpreview.testPreview

fun NavGraphBuilder.tests(navController: NavController) {
    navigation(route = ApplicationRoute.Tests.route, startDestination = TestsRoute.Main.route) {
        mainRouting(navController)
        testPreview(navController)
    }
}