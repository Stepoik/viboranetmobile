package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.tests.presentation.TestsRoute
import stepan.gorokhov.viboranet.tests.presentation.main.components.SearchButton
import stepan.gorokhov.viboranet.tests.presentation.main.components.tests
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.navigateOngoingTest
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.verticalSpacer
import viboranet.features.home.tests.presentation.generated.resources.Res
import viboranet.features.home.tests.presentation.generated.resources.tests

@Composable
fun MainTestsScreen(navController: NavController) {
    val viewModel = koinViewModel<MainTestsViewModel>()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(MainTestsEvent.LoadTests)

        viewModel.effect.collect { effect ->
            when (effect) {
                is MainTestsEffect.NavigateTest -> {
                    navController.navigateOngoingTest(effect.id)
                }

                is MainTestsEffect.NavigateSearch -> {

                }

                is MainTestsEffect.NavigateCreateTest -> navController.navigate(TestsRoute.CreateTest.route)
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
            val eventHandler = rememberUIEventHandler(viewModel)
            MainTestsScreen(state = state, eventHandler = eventHandler)
        }
    }
}

@Composable
internal fun MainTestsScreen(
    state: MainTestsState,
    eventHandler: EventHandler<MainTestsEvent>
) {
    BaseScaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { eventHandler.handleEvent(MainTestsEvent.CreateTestClicked) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Создать тест"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                stringResource(Res.string.tests),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            SearchButton(
                onClick = { eventHandler.handleEvent(MainTestsEvent.SearchClicked) },
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn(Modifier.fillMaxSize().weight(1f)) {
                verticalSpacer(16.dp)
                tests(
                    state.tests,
                    onTestClicked = { eventHandler.handleEvent(MainTestsEvent.TestClicked(it)) })
            }
        }
    }
}

@Composable
expect fun MainTestsScreenPreview()