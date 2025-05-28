package stepan.gorokhov.viboranet.home.route

import stepan.gorokhov.viboranet.coreui.routing.BaseRoute

sealed class HomeRoute : BaseRoute {
    data object Tests : HomeRoute() {
        override val route: String = "tests"
    }
    data object Chat : HomeRoute() {
        override val route: String = "chat"
    }
    data object Profile : HomeRoute() {
        override val route: String = "profile"
    }

    data object Leaderboard : HomeRoute() {
        override val route: String = "leaderboard"
    }
}