package stepan.gorokhov.viboranet.tests.presentation.ongoingTest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.screens.FinishingScreen
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.screens.OngoingTestScreen
import stepan.gorokhov.viboranet.tests.presentation.testResult.navigateTestResult

@Composable
internal fun OngoingTestScreen(navController: NavController) {
    val viewModel = koinViewModel<OngoingTestViewModel>()
    val eventHandler = rememberUIEventHandler(viewModel)
    LaunchedEffect(Unit) {
        viewModel.handleEvent(OngoingTestEvent.StartTest)

        viewModel.effect.collect { effect ->
            when (effect) {
                is OngoingTestEffect.NavigateResult -> navController.navigateTestResult(effect.resultId) { popUpTo(TestsRoute.Main.route) }
            }
        }
    }
    when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
        is OngoingTestState.Started -> {
            OngoingTestScreen(state, eventHandler)
        }

        is OngoingTestState.Finishing -> {
            FinishingScreen()
        }

        else -> {}
    }
}