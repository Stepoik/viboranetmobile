package stepan.gorokhov.viboranet.tests.presentation.testResult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.coil.AsyncImage
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.tests.api.models.TournamentResultOption
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold

@Composable
fun TestResultScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<TestResultViewModel>()
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleEvent(TestResultEvent.LoadResult)
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                TestResultEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }

    TestResultScreen(state, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TestResultScreen(state: TestResultState, eventHandler: EventHandler<TestResultEvent>) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = { Text("Результаты теста", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { eventHandler.handleEvent(TestResultEvent.NavigateBackClicked) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) {
        when (state) {
            is TestResultState.Idle -> Unit
            is TestResultState.Loading -> {}
            is TestResultState.Error -> {}
            is TestResultState.ResultLoaded -> ResultContent(
                options = state.options
            )
        }
    }
}

@Composable
private fun ResultContent(
    options: List<TournamentResultOption>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options) { option ->
            ResultItem(
                option = option,
                position = options.indexOf(option) + 1
            )
        }
    }
}

@Composable
private fun ResultItem(
    option: TournamentResultOption,
    position: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Позиция
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = position.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            AsyncImage(
                model = option.image,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = option.title,
                    style = MaterialTheme.typography.titleMedium
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Ваш балл",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = option.localScore.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Общий балл",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = option.globalScore.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}