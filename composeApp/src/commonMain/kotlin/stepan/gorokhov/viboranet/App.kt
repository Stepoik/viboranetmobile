package stepan.gorokhov.viboranet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import stepan.gorokhov.viboranet.auth.presentation.auth
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.splash.splash
import stepan.gorokhov.viboranet.tests.presentation.tests
import stepan.gorokhov.viboranet.uikit.ViboraNetTheme

@Composable
fun App() {
    ViboraNetTheme {
        val navController = rememberNavController()

        NavHost(navController, startDestination = ApplicationRoute.Splash.route) {
            splash(navController)
            auth(navController)
            tests(navController)
        }
    }
}