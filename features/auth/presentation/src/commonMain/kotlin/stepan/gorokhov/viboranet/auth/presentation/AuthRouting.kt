package stepan.gorokhov.viboranet.auth.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import stepan.gorokhov.viboranet.auth.presentation.signin.signIn
import stepan.gorokhov.viboranet.auth.presentation.signup.signUp
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute

fun NavGraphBuilder.auth(navController: NavController) {
    navigation(route = ApplicationRoute.Auth.route, startDestination = AuthRoute.SignUp.route) {
        signIn(navController)
        signUp(navController)
    }
}