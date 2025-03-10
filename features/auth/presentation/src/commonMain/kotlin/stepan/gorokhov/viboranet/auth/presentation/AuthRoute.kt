package stepan.gorokhov.viboranet.auth.presentation

import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.coreui.routing.BaseRoute

private val BASE_ROUTE = ApplicationRoute.Auth.route

sealed class AuthRoute(override val route: String): BaseRoute {
    data object SignIn : AuthRoute("$BASE_ROUTE/sign_in")
    data object SignUp : AuthRoute("$BASE_ROUTE/sign_up")
}