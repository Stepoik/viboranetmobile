package stepan.gorokhov.viboranet.tests.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import stepan.gorokhov.viboranet.home.route.HomeRoute
import stepan.gorokhov.viboranet.tests.presentation.createTest.createTest
import stepan.gorokhov.viboranet.tests.presentation.main.mainRouting
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.ongoingTest
import stepan.gorokhov.viboranet.tests.presentation.search.search
import stepan.gorokhov.viboranet.tests.presentation.testResult.testResult
import stepan.gorokhov.viboranet.tests.presentation.testpreview.testPreview

fun NavGraphBuilder.tests(navController: NavController) {
    navigation(route = HomeRoute.Tests.route, startDestination = TestsRoute.Main.route) {
        mainRouting(navController)
        testPreview(navController)
        ongoingTest(navController)
        testResult(navController)
        createTest(navController)
        search(navController)
    }
}