package stepan.gorokhov.viboranet.leaderboard.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.leaderboard.presentation.components.LeaderboardItem
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.VerticalSpacer

@Composable
fun LeaderboardScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<LeaderboardViewModel>()
    val state = viewModel.state.collectAsState().value
    val eventHandler = rememberUIEventHandler(viewModel)

    LaunchedEffect(Unit) {
        eventHandler.handleEvent(LeaderboardEvent.LoadLeaderboard)
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LeaderboardEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    LeaderboardScreen(
        state = state,
        eventHandler = eventHandler
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LeaderboardScreen(
    state: LeaderboardViewModelState,
    eventHandler: EventHandler<LeaderboardEvent>
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = { Text("Таблица лидеров", fontSize = 20.sp) }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when {
                state.loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.error,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        VerticalSpacer(16.dp)
                        Button(
                            onClick = { eventHandler.handleEvent(LeaderboardEvent.Refresh) }
                        ) {
                            Text("Повторить")
                        }
                    }
                }
                state.leaderboard.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Таблица лидеров пуста",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        itemsIndexed(state.leaderboard) { index, item ->
                            LeaderboardItem(
                                item = item,
                                position = index
                            )
                        }
                    }
                }
            }
        }
    }
}