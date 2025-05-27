package stepan.gorokhov.viboranet.chat

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlin.collections.plus as kPlus
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.chat.api.ChatConnection
import stepan.gorokhov.viboranet.chat.api.ChatRepository
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.core.list.plus
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val chatConnection: ChatConnection,
    private val userRepository: UserRepository
) : BaseViewModel<ChatState, ChatViewModelState, ChatEffect, ChatEvent>() {
    private var connectionJob: Job? = null

    override fun getInitialState(): ChatViewModelState = ChatViewModelState()

    override fun handleEvent(event: ChatEvent) {
        viewModelScope.launch {
            when (event) {
                is ChatEvent.LoadMessages -> loadMessages()

                is ChatEvent.UpdateInputText -> {
                    _state.update { it.copy(inputText = event.text) }
                }

                is ChatEvent.SendMessage -> sendMessage()

                is ChatEvent.ConnectChat -> connectToChat()

                is ChatEvent.DisconnectChat -> disconnectChat()
            }
        }
    }

    private fun connectToChat() {
        connectionJob?.cancel()
        connectionJob = viewModelScope.launch {
            val user = userRepository.getUser().getOrElse { return@launch }
            chatConnection.subscribe().collect { message ->
                println(message)
                _state.update {
                    it.copy(messages = message.toVO(user.id) + it.messages)
                }
            }
        }
    }

    private fun disconnectChat() {
        connectionJob?.cancel()
    }

    private suspend fun loadMessages() {
        val state = _state.value
        if (state.isLoading) return

        _state.update { it.copy(isLoading = true) }
        chatRepository.getMessages(offset = state.messages.size.toLong()).onSuccess { messages ->
            val user = userRepository.getUser().getOrNull() ?: return@onSuccess
            val messagesVo = messages.map { it.toVO(user.id) }
            _state.update { it.copy(messages = it.messages.kPlus(messagesVo)) }
        }
        _state.update { it.copy(isLoading = false) }
    }

    private suspend fun sendMessage() {
        val currentText = _state.value.inputText

        if (currentText.isNotBlank()) {
            chatConnection.sendMessage(currentText)
        }
        _state.update { it.copy(inputText = "") }
    }
} 