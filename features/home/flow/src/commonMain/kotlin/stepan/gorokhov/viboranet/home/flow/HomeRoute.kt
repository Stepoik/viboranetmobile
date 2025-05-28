package stepan.gorokhov.viboranet.home.flow

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.home.route.HomeRoute

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
) {
    data object Tests : BottomNavItem(
        route = HomeRoute.Tests.route,
        title = "Тесты",
        icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
    )
    data object Chat : BottomNavItem(
        route = HomeRoute.Chat.route,
        title = "Чат",
        icon = { Icon(Icons.Default.Menu, contentDescription = null) }
    )
    data object Profile : BottomNavItem(
        route = HomeRoute.Profile.route,
        title = "Профиль",
        icon = { Icon(Icons.Default.Person, contentDescription = null) }
    )
    data object Leaderboard : BottomNavItem(
        route = HomeRoute.Leaderboard.route,
        title = "Лидерборд",
        icon = { Icon(Icons.Default.Star, contentDescription = null) }
    )
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Tests,
        BottomNavItem.Chat,
        BottomNavItem.Profile,
        BottomNavItem.Leaderboard
    )
    val currentDestination =
        navController.currentBackStackEntryAsState().value?.destination
    NavigationBar {
        items.forEach { item ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == item.route } == true
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.title) },
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.home(parentNavController: NavController) {
    composable(ApplicationRoute.Home.route) {
        HomeScreen(parentNavController)
    }
}