package stepan.gorokhov.viboranet.auth.presentation.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.auth.presentation.AuthRoute

internal fun NavGraphBuilder.signIn(navController: NavController) {
    composable(AuthRoute.SignIn.route) {
        SignInScreen(navController)
    }
}