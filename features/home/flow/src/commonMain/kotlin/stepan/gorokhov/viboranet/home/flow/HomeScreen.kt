package stepan.gorokhov.viboranet.home.flow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import stepan.gorokhov.viboranet.chat.chat
import stepan.gorokhov.viboranet.home.route.HomeRoute
import stepan.gorokhov.viboranet.leaderboard.presentation.leaderboard
import stepan.gorokhov.viboranet.profile.profile
import stepan.gorokhov.viboranet.tests.presentation.tests

@Composable
fun HomeScreen(parentNavController: NavController) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeRoute.Tests.route
            ) {
                tests(navController)
                chat()
                profile(navController)
                leaderboard(navController)
            }
        }
    }
} 