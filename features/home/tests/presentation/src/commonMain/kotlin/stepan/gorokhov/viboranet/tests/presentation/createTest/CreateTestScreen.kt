package stepan.gorokhov.viboranet.tests.presentation.createTest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.VerticalSpacer
import stepan.gorokhov.viboranet.uikit.components.verticalSpacer

@Composable
fun CreateTestScreen(navController: NavController) {
    val viewModel = koinViewModel<CreateTestViewModel>()
    val state = viewModel.state.collectAsState().value
    val eventHandler = rememberUIEventHandler(viewModel)

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
    BaseScaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = state.title,
                    onValueChange = { eventHandler.handleEvent(CreateTestEvent.UpdateTitle(it)) },
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

            item {
                ImageUploadSection(
                    currentImage = state.testImage,
                    onImageSelected = { eventHandler.handleEvent(CreateTestEvent.UpdateTestImage(it)) }
                )
            }
            verticalSpacer(16.dp)

            items(state.answerOptions) { answerOption ->
                AnswerOptionItem(
                    answerOption = answerOption,
                    onUpdate = { eventHandler.handleEvent(CreateTestEvent.UpdateAnswerOption(state.answerOptions.indexOf(answerOption), it)) },
                    onDelete = { eventHandler.handleEvent(CreateTestEvent.RemoveAnswerOption(state.answerOptions.indexOf(answerOption))) }
                )
                VerticalSpacer(8.dp)
            }

            item {
                Button(
                    onClick = { eventHandler.handleEvent(CreateTestEvent.AddAnswerOption(AnswerOption("", "", null))) },
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
    onImageSelected: (String) -> Unit
) {
    Column {
        Text("Изображение теста", style = MaterialTheme.typography.titleMedium)
        VerticalSpacer(8.dp)
        
        if (currentImage != null) {
            // Здесь должна быть загрузка и отображение изображения
            Text("Изображение загружено")
        }
        
        Button(
            onClick = { /* Здесь должна быть логика выбора изображения */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Выбрать изображение")
        }
    }
}

@Composable
private fun AnswerOptionItem(
    answerOption: AnswerOption,
    onUpdate: (AnswerOption) -> Unit,
    onDelete: () -> Unit
) {
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
            
            if (answerOption.image != null) {
                // Здесь должна быть загрузка и отображение изображения
                Text("Изображение загружено")
            }
            
            Button(
                onClick = { /* Здесь должна быть логика выбора изображения */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Выбрать изображение")
            }
        }
    }
} 