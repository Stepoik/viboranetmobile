package stepan.gorokhov.viboranet.coreui.routing

interface BaseRoute {
    val route: String
    val navRoute: String get() = route
}