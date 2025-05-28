package stepan.gorokhov.viboranet.tests.presentation.createTest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import stepan.gorokhov.viboranet.coreui.coil.AsyncImage
import stepan.gorokhov.viboranet.coreui.launchers.rememberImageCaptureLauncher
import stepan.gorokhov.viboranet.coreui.models.StableImage
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.VerticalSpacer
import stepan.gorokhov.viboranet.uikit.components.verticalSpacer

@Composable
fun CreateTestScreen(navController: NavController, testId: String?) {
    val viewModel = koinViewModel<CreateTestViewModel>(parameters = { parametersOf(testId) })
    val state = viewModel.state.collectAsState().value
    val eventHandler = rememberUIEventHandler(viewModel)

    LaunchedEffect(Unit) {
        viewModel.handleEvent(CreateTestEvent.LoadTest)
        viewModel.effect.collect {
            when (it) {
                is CreateTestEffect.NavigateBack -> navController.navigateUp()
                else -> {}
            }
        }
    }

    when {
        state.loading -> {
            CircularProgressIndicator()
        }

        state.error != null -> {
            Text(text = state.error)
        }

        else -> {
            CreateTestContent(state = state, eventHandler = eventHandler)
        }
    }
}

@Composable
private fun CreateTestContent(
    state: CreateTestState,
    eventHandler: EventHandler<CreateTestEvent>
) {
    val saveableStateHolder = rememberSaveableStateHolder()
    BaseScaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = state.title,
                    onValueChange = { eventHandler.handleEvent(CreateTestEvent.UpdateTitle(it)) },
                    singleLine = true,
                    label = { Text("Название теста") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            verticalSpacer(16.dp)

            item {
                OutlinedTextField(
                    value = state.description,
                    onValueChange = { eventHandler.handleEvent(CreateTestEvent.UpdateDescription(it)) },
                    label = { Text("Описание теста") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
            verticalSpacer(16.dp)

            item {
                TagsSection(
                    tags = state.tags,
                    onAddTag = { eventHandler.handleEvent(CreateTestEvent.AddTag(it)) },
                    onRemoveTag = { eventHandler.handleEvent(CreateTestEvent.RemoveTag(it)) }
                )
            }
            verticalSpacer(16.dp)

            item(key = "image_section") {
                ImageUploadSection(
                    currentImage = state.testImage,
                    onImageSelected = { eventHandler.handleEvent(CreateTestEvent.UpdateTestImage(it)) }
                )
            }
            verticalSpacer(16.dp)

            items(
                state.answerOptions,
                key = { item -> item.id }) { answerOption ->
                AnswerOptionItem(
                    answerOption = answerOption,
                    onUpdate = {
                        eventHandler.handleEvent(
                            CreateTestEvent.UpdateAnswerOption(it)
                        )
                    },
                    onLoadImage = {
                        eventHandler.handleEvent(
                            CreateTestEvent.UpdateAnswerOptionImage(
                                id = answerOption.id,
                                image = it
                            )
                        )
                    },
                    onDelete = {
                        eventHandler.handleEvent(
                            CreateTestEvent.RemoveAnswerOption(answerOption.id)
                        )
                    }
                )
                VerticalSpacer(8.dp)
            }

            item {
                Button(
                    onClick = {
                        eventHandler.handleEvent(
                            CreateTestEvent.AddAnswerOption(AnswerOption())
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Добавить вариант ответа")
                }
            }
            verticalSpacer(16.dp)

            item {
                Button(
                    onClick = { eventHandler.handleEvent(CreateTestEvent.CreateTest) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Создать тест")
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagsSection(
    tags: List<String>,
    onAddTag: (String) -> Unit,
    onRemoveTag: (String) -> Unit
) {
    var newTag by remember { mutableStateOf("") }

    Column {
        Text("Теги", style = MaterialTheme.typography.titleMedium)
        VerticalSpacer(8.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newTag,
                onValueChange = { newTag = it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                label = { Text("Новый тег") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newTag.isNotBlank()) {
                    onAddTag(newTag)
                    newTag = ""
                }
            }) {
                Text("Добавить")
            }
        }

        VerticalSpacer(8.dp)

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tags.forEach { tag ->
                InputChip(
                    selected = false,
                    onClick = { },
                    label = { Text(tag) },
                    trailingIcon = {
                        IconButton(onClick = { onRemoveTag(tag) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Удалить тег")
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ImageUploadSection(
    currentImage: String?,
    onImageSelected: (StableImage) -> Unit
) {
    val launcher = rememberImageCaptureLauncher(onSuccess = {
        onImageSelected(it)
    }, onCancel = {})
    Column {
        Text("Изображение теста", style = MaterialTheme.typography.titleMedium)
        VerticalSpacer(8.dp)

        LoadImageButton(
            currentImage,
            onLoadImage = launcher::launch,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AnswerOptionItem(
    answerOption: AnswerOption,
    onUpdate: (AnswerOption) -> Unit,
    onLoadImage: (StableImage) -> Unit,
    onDelete: () -> Unit
) {
    val pickImageLauncher = rememberImageCaptureLauncher(onSuccess = {
        onLoadImage(it)
    }, onCancel = {})
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Вариант ответа", style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Удалить вариант")
                }
            }

            VerticalSpacer(8.dp)

            OutlinedTextField(
                value = answerOption.title,
                onValueChange = { onUpdate(answerOption.copy(title = it)) },
                label = { Text("Заголовок") },
                modifier = Modifier.fillMaxWidth()
            )

            VerticalSpacer(8.dp)

            OutlinedTextField(
                value = answerOption.description,
                onValueChange = { onUpdate(answerOption.copy(description = it)) },
                label = { Text("Описание") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            VerticalSpacer(8.dp)

            LoadImageButton(
                answerOption.imageUrl,
                onLoadImage = pickImageLauncher::launch,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LoadImageButton(
    image: String?,
    onLoadImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        onClick = onLoadImage,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.aspectRatio(16f / 9f)
    ) {
        if (image == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        } else {
            AsyncImage(image, contentDescription = null, modifier = Modifier.fillMaxSize())
        }
    }
}