package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import kotlinx.coroutines.channels.consumeEach
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.tests.presentation.testpreview.navigateTestPreview

@Composable
fun MainTestsScreen(navController: NavController) {
    val viewModel = koinViewModel<MainTestsViewModel>()
    LaunchedEffect(Unit) {
        viewModel.sendEvent(MainTestsEvent.LoadTests)

        viewModel.effect.consumeEach {
            when (it) {
                is MainTestsEffect.NavigateTest -> {
                    navController.navigateTestPreview(it.id)
                }
                null -> {}
            }
        }
    }

    val state = viewModel.state.collectAsState().value
    when {
        state.loading -> {
            MainTestsLoadingSkeleton()
        }

        state.error != null -> {
            MainTestsErrorScreen()
        }

        else -> {
            MainTestsScreen(state)
        }
    }
}

@Composable
private fun MainTestsScreen(state: MainTestsState) {

}