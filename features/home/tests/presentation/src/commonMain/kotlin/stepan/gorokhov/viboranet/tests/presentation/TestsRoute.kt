package stepan.gorokhov.viboranet.tests.presentation

import stepan.gorokhov.viboranet.coreui.routing.BaseRoute
import stepan.gorokhov.viboranet.home.route.HomeRoute

private val BASE_ROUTE = HomeRoute.Tests.route

internal object TestsRoutesArguments {
    const val ID = "id"
}

internal sealed class TestsRoute(override val route: String, override val navRoute: String = route) :
    BaseRoute {
    data object Main : TestsRoute("$BASE_ROUTE/main")
    data object TestPreview : TestsRoute(
        route = "$BASE_ROUTE/preview/{${TestsRoutesArguments.ID}}",
        navRoute = "$BASE_ROUTE/preview"
    )

    data object OngoingTest : TestsRoute(
        route = "$BASE_ROUTE/ongoing/{${TestsRoutesArguments.ID}}",
        navRoute = "$BASE_ROUTE/ongoing"
    )

    data object TestResult : TestsRoute(
        route = "$BASE_ROUTE/result/{${TestsRoutesArguments.ID}}",
        navRoute = "$BASE_ROUTE/result"
    )

    data object CreateTest : TestsRoute(
        route = "$BASE_ROUTE/create"
    )
}