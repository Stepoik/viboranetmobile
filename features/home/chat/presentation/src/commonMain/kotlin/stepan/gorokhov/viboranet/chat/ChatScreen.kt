package stepan.gorokhov.viboranet.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.VerticalSpacer

@Composable
fun ChatScreen() {
    val viewModel = koinViewModel<ChatViewModel>()
    val state = viewModel.state.collectAsState().value
    val eventHandler = rememberUIEventHandler(viewModel)

    LaunchedEffect(Unit) {
        eventHandler.handleEvent(ChatEvent.LoadMessages)
    }

    DisposableEffect(Unit) {
        eventHandler.handleEvent(ChatEvent.ConnectChat)

        onDispose {
            eventHandler.handleEvent(ChatEvent.DisconnectChat)
        }
    }

    when {
        state.isLoading -> {
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
            ChatContent(
                state = state,
                onInputTextChange = { eventHandler.handleEvent(ChatEvent.UpdateInputText(it)) },
                onSendClick = { eventHandler.handleEvent(ChatEvent.SendMessage) }
            )
        }
    }
}

@Composable
private fun ChatContent(
    state: ChatState,
    onInputTextChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    BaseScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Список сообщений
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true
            ) {
                items(state.messages) { message ->
                    MessageItem(message = message)
                    VerticalSpacer(8.dp)
                }
            }

            VerticalSpacer(16.dp)

            // Поле ввода
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.inputText,
                    onValueChange = onInputTextChange,
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Введите сообщение") },
                    maxLines = 5
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = onSendClick,
                    enabled = state.inputText.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Отправить"
                    )
                }
            }
        }
    }
}

@Composable
private fun MessageItem(message: MessageVO) {
    val backgroundColor = if (message.isFromMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }
    val textColor = if (message.isFromMe) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.isFromMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(backgroundColor)
                .padding(12.dp),
            horizontalAlignment = if (message.isFromMe) Alignment.End else Alignment.Start
        ) {
            // Имя автора
            if (!message.isFromMe) {
                Text(
                    text = message.author.username,
                    style = MaterialTheme.typography.labelMedium,
                    color = textColor.copy(alpha = 0.7f)
                )
                VerticalSpacer(4.dp)
            }
            
            // Текст сообщения
            Text(
                text = message.text,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge
            )
            
            // Время отправки
            Text(
                text = message.time,
                style = MaterialTheme.typography.labelSmall,
                color = textColor.copy(alpha = 0.7f)
            )
        }
    }
}

