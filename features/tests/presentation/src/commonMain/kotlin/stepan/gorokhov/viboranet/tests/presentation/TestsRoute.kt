package stepan.gorokhov.viboranet.tests.presentation

import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.coreui.routing.BaseRoute

private val BASE_ROUTE = ApplicationRoute.Auth.route

object TestsRoutesArguments {
    const val ID = "id"
}

sealed class TestsRoute(override val route: String, override val navRoute: String = route) : BaseRoute {
    data object Main : TestsRoute("$BASE_ROUTE/main")
    data object TestPreview : TestsRoute("$BASE_ROUTE/preview/{${TestsRoutesArguments.ID}}", navRoute = "$BASE_ROUTE/preview")
}