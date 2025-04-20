package stepan.gorokhov.viboranet.tests.presentation

import stepan.gorokhov.viboranet.coreui.routing.BaseRoute
import stepan.gorokhov.viboranet.home.route.HomeRoute

private val BASE_ROUTE = HomeRoute.Tests.route

object TestsRoutesArguments {
    const val ID = "id"
}

sealed class TestsRoute(override val route: String, override val navRoute: String = route) : BaseRoute {
    data object Main : TestsRoute("$BASE_ROUTE/main")
    data object TestPreview : TestsRoute("$BASE_ROUTE/preview/{${TestsRoutesArguments.ID}}", navRoute = "$BASE_ROUTE/preview")
}