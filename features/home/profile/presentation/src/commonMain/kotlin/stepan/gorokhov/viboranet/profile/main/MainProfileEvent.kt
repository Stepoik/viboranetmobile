package stepan.gorokhov.viboranet.profile.main

import stepan.gorokhov.viboranet.coreui.mvi.UIEvent

sealed class MainProfileEvent : UIEvent {
    object LoadProfile : MainProfileEvent()
    data class UpdateAvatar(val avatarUrl: String) : MainProfileEvent()
    data class UpdateUsername(val username: String) : MainProfileEvent()
} 