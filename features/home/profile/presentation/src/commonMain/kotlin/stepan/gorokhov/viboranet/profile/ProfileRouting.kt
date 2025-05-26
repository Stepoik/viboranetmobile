package stepan.gorokhov.viboranet.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import stepan.gorokhov.viboranet.coreui.routing.BaseRoute
import stepan.gorokhov.viboranet.home.route.HomeRoute
import stepan.gorokhov.viboranet.profile.main.main

private val BASE_ROUTE = HomeRoute.Profile.route

sealed class ProfileRoute(override val route: String) : BaseRoute {
    data object Main : ProfileRoute("$BASE_ROUTE/main")
}

fun NavGraphBuilder.profile(navController: NavController) {
    navigation(route = HomeRoute.Profile.route, startDestination = ProfileRoute.Main.route) {
        main(navController)
    }
}