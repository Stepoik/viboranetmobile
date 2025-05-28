package stepan.gorokhov.viboranet.tests.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.tests.presentation.main.components.TestPreviewItem
import stepan.gorokhov.viboranet.tests.presentation.main.components.tests
import stepan.gorokhov.viboranet.tests.presentation.testpreview.navigateTestPreview
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.navigateOngoingTest
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.TextFieldWithIcon
import stepan.gorokhov.viboranet.uikit.components.VerticalSpacer

@Composable
fun SearchScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val state = viewModel.state.collectAsState().value
    val eventHandler = rememberUIEventHandler(viewModel)

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchEffect.NavigateTest -> navController.navigateOngoingTest(effect.id)
                is SearchEffect.NavigateTestPreview -> navController.navigateTestPreview(effect.id)
                is SearchEffect.NavigateBack -> navController.navigateUp()
            }
        }
    }
    SearchScreen(state = state, eventHandler = eventHandler)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(state: SearchViewModelState, eventHandler: EventHandler<SearchEvent>) {
    val listState = rememberLazyListState()
    // Обработка прокрутки для пагинации
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastIndex ->
            if (lastIndex != null && lastIndex >= state.tests.size - 3) {
                eventHandler.handleEvent(SearchEvent.LoadMore)
            }
        }
    }

    BaseScaffold(
        topBar = {
            TopAppBar(
                title = { Text("Поиск тестов", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { eventHandler.handleEvent(SearchEvent.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val focusRequester = remember {
                FocusRequester()
            }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            // Поле поиска
            TextFieldWithIcon(
                value = state.searchQuery,
                onValueChanged = { eventHandler.handleEvent(SearchEvent.QueryChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(focusRequester),
                placeholder = "Поиск тестов...",
                icon = Icons.Default.Search
            )

            // Результаты поиска
            when {
                state.loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.error,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                state.tests.isEmpty() && state.searchQuery.isNotEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ничего не найдено",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                state.tests.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Введите запрос для поиска",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tests(
                            state.tests,
                            onTestClicked = { eventHandler.handleEvent(SearchEvent.TestClicked(it)) },
                            onStartClicked = {
                                eventHandler.handleEvent(
                                    SearchEvent.StartTestClicked(it)
                                )
                            })

                        if (state.isLoadingMore) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}