package stepan.gorokhov.viboranet.profile.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.profile.ProfileRoute

fun NavGraphBuilder.main(navController: NavController) {
    composable(ProfileRoute.Main.route) {
        MainProfileScreen(navController)
    }
}