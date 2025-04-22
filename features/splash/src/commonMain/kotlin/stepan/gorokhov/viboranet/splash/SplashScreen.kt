package stepan.gorokhov.viboranet.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.coreui.routing.popUpGraph

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = koinViewModel<SplashViewModel>()
    LaunchedEffect(Unit) {
        viewModel.sendEvent(SplashEvent.CheckUser)
        viewModel.effect.collect { effect ->
            when (effect) {
                is SplashEffect.NavigateAuth -> {
                    navController.navigate(ApplicationRoute.Auth.route) { popUpGraph() }
                }

                is SplashEffect.NavigateTests -> {
                    navController.navigate(ApplicationRoute.Home.route) { popUpGraph() }
                }
            }
        }
    }
}