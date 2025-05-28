package stepan.gorokhov.viboranet.leaderboard.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.home.route.HomeRoute

fun NavGraphBuilder.leaderboard(navController: NavController) {
    composable(HomeRoute.Leaderboard.route) {
        LeaderboardScreen(navController)
    }
}