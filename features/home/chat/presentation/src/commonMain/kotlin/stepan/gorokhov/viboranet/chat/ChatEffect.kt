package stepan.gorokhov.viboranet.chat

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect

sealed class ChatEffect : UIEffect {
    data class ShowError(val message: String) : ChatEffect()
} 