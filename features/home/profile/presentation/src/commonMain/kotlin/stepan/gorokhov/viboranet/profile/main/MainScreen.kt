package stepan.gorokhov.viboranet.profile.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.coil.AsyncImage
import stepan.gorokhov.viboranet.coreui.launchers.rememberImageCaptureLauncher
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.profile.ProfileRoute
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.VerticalSpacer

@Composable
fun MainProfileScreen(navController: NavController) {
    val viewModel = koinViewModel<MainProfileViewModel>()
    val state = viewModel.state.collectAsState().value

    val eventHandler = rememberUIEventHandler(viewModel)

    LaunchedEffect(Unit) {
        eventHandler.handleEvent(MainProfileEvent.LoadProfile)

        viewModel.effect.collect {
            when (it) {
                is MainProfileEffect.NavigateEditTest -> navController.navigate("${ProfileRoute.EditTest.navRoute}/${it.testId}")
                else -> {}
            }
        }
    }

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
                Text(text = state.error)
            }
        }

        else -> {
            MainProfileContent(state = state, eventHandler = eventHandler)
        }
    }
}

@Composable
private fun MainProfileContent(
    state: MainProfileState,
    eventHandler: EventHandler<MainProfileEvent>
) {
    var showEditNameDialog by remember { mutableStateOf(false) }
    var newUsername by remember { mutableStateOf(state.username) }
    val launcher = rememberImageCaptureLauncher(
        onSuccess = { eventHandler.handleEvent(MainProfileEvent.UpdateAvatar(it)) },
        onCancel = {}
    )
    BaseScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                AsyncImage(
                    model = state.avatarUrl,
                    contentDescription = "Аватар пользователя",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = launcher::launch,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Изменить фото",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            VerticalSpacer(16.dp)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.username,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                IconButton(
                    onClick = { showEditNameDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Изменить имя",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            VerticalSpacer(24.dp)

            // Список созданных тестов
            if (state.createdTests.isNotEmpty()) {
                Text(
                    text = "Созданные тесты",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.align(Alignment.Start)
                )

                VerticalSpacer(8.dp)

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.createdTests) { test ->
                        CreatedTestItem(test = test, eventHandler = eventHandler)
                    }

                    if (state.hasMoreTests) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                if (state.isLoadingMore) {
                                    CircularProgressIndicator()
                                } else {
                                    TextButton(
                                        onClick = { eventHandler.handleEvent(MainProfileEvent.LoadMoreTests) }
                                    ) {
                                        Text("Загрузить еще")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showEditNameDialog) {
        AlertDialog(
            onDismissRequest = { showEditNameDialog = false },
            title = { Text("Изменить имя") },
            text = {
                OutlinedTextField(
                    value = newUsername,
                    onValueChange = { newUsername = it },
                    label = { Text("Имя пользователя") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        eventHandler.handleEvent(MainProfileEvent.UpdateUsername(newUsername))
                        showEditNameDialog = false
                    }
                ) {
                    Text("Сохранить")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showEditNameDialog = false }
                ) {
                    Text("Отмена")
                }
            }
        )
    }
}

@Composable
private fun CreatedTestItem(test: TestPreview, eventHandler: EventHandler<MainProfileEvent>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { eventHandler.handleEvent(MainProfileEvent.NavigateEditTest(test.id)) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = test.image,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = test.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = test.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Рейтинг",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = test.globalRating.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

