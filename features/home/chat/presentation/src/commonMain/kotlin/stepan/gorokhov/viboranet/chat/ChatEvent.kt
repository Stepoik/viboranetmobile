package stepan.gorokhov.viboranet.chat

import stepan.gorokhov.viboranet.coreui.mvi.UIEvent

sealed class ChatEvent : UIEvent {
    data object LoadMessages : ChatEvent()
    data class UpdateInputText(val text: String) : ChatEvent()
    data object SendMessage : ChatEvent()
    data object ConnectChat : ChatEvent()
    data object DisconnectChat : ChatEvent()
} 