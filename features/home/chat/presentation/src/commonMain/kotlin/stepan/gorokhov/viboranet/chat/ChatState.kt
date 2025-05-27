package stepan.gorokhov.viboranet.chat

import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState

data class MessageVO(
    val id: String,
    val text: String,
    val isFromMe: Boolean,
    val time: String,
    val author: AuthorVO
)

data class AuthorVO(
    val username: String
)

data class ChatState(
    val messages: List<MessageVO> = emptyList(),
    val inputText: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) : UIState

data class ChatViewModelState(
    val messages: List<MessageVO> = emptyList(),
    val inputText: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) : ViewModelState<ChatState> {
    override fun toScreenState(): ChatState = ChatState(
        messages = messages,
        inputText = inputText,
        isLoading = isLoading,
        error = error
    )
} 