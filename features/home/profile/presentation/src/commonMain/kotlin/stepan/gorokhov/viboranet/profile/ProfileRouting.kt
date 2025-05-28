package stepan.gorokhov.viboranet.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import stepan.gorokhov.viboranet.coreui.routing.BaseRoute
import stepan.gorokhov.viboranet.home.route.HomeRoute
import stepan.gorokhov.viboranet.profile.main.main
import stepan.gorokhov.viboranet.tests.presentation.createTest.CreateTestScreen

private val BASE_ROUTE = HomeRoute.Profile.route

sealed class ProfileRoute(override val route: String, override val navRoute: String = route) :
    BaseRoute {
    data object Main : ProfileRoute("$BASE_ROUTE/main")
    data object EditTest :
        ProfileRoute("$BASE_ROUTE/edit_test/{id}", navRoute = "$BASE_ROUTE/edit_test")
}

fun NavGraphBuilder.profile(navController: NavController) {
    navigation(route = HomeRoute.Profile.route, startDestination = ProfileRoute.Main.route) {
        main(navController)
        composable(
            ProfileRoute.EditTest.route,
            arguments = listOf(navArgument("id", { type = NavType.StringType }))
        ) {
            val testId = it.arguments?.getString("id") ?: ""
            CreateTestScreen(navController, testId = testId)
        }
    }
}