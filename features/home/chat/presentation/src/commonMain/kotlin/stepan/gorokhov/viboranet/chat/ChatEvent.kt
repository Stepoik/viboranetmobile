package stepan.gorokhov.viboranet.chat

import stepan.gorokhov.viboranet.coreui.mvi.UIEvent

sealed class ChatEvent : UIEvent {
    object LoadMessages : ChatEvent()
    data class UpdateInputText(val text: String) : ChatEvent()
    object SendMessage : ChatEvent()
} 