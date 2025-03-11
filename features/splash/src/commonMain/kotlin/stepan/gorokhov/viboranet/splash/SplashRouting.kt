package stepan.gorokhov.viboranet.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute

fun NavGraphBuilder.splash(navController: NavController) {
    composable(ApplicationRoute.Splash.route) {
        SplashScreen(navController)
    }
}