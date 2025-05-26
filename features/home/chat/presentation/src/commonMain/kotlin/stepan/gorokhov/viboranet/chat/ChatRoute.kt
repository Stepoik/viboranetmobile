package stepan.gorokhov.viboranet.chat

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.home.route.HomeRoute

fun NavGraphBuilder.chat() {
    composable(HomeRoute.Chat.route) {
        ChatScreen()
    }
}