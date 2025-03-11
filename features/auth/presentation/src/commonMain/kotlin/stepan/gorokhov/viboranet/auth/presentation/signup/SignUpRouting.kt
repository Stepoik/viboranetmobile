package stepan.gorokhov.viboranet.auth.presentation.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import stepan.gorokhov.viboranet.auth.presentation.AuthRoute

internal fun NavGraphBuilder.signUp(navController: NavController) {
    composable(AuthRoute.SignUp.route) {
        SignUpScreen(navController)
    }
}