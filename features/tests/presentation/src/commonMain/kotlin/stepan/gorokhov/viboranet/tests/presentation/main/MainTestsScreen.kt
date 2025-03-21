package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.channels.consumeEach
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.tests.presentation.main.components.SearchButton
import stepan.gorokhov.viboranet.tests.presentation.main.components.TestCard
import stepan.gorokhov.viboranet.tests.presentation.testpreview.navigateTestPreview
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold

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
internal fun MainTestsScreen(state: MainTestsState) {
    BaseScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            SearchButton(onClick = {})

            Spacer(modifier = Modifier.height(24.dp))

            Text("Рекомендуем", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            val pagerState = rememberPagerState(pageCount = { state.recommended.size })

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier.height(200.dp)
            ) { page ->
                TestCard(test = state.recommended[page], onClick = { })
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Популярные", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                state.popular.forEach { test ->
                    TestCard(
                        test = test,
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
expect fun MainTestsScreenPreview()