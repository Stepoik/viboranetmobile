package stepan.gorokhov.viboranet.common.presentation

import stepan.gorokhov.viboranet.coreui.routing.BaseRoute

sealed class ApplicationRoute(override val route: String) : BaseRoute {
    data object Splash : ApplicationRoute("splash")
    data object Auth : ApplicationRoute("auth")
    data object Home : ApplicationRoute("home")
}