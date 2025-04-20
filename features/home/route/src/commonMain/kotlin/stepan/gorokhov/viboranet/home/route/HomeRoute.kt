package stepan.gorokhov.viboranet.home.route

import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.coreui.routing.BaseRoute

private val BASE_ROUTE = ApplicationRoute.Home.route

sealed class HomeRoute(override val route: String) : BaseRoute {
    data object Tests : HomeRoute("$BASE_ROUTE/tests")
    data object Profile : HomeRoute("$BASE_ROUTE/profile")
}