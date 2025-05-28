package stepan.gorokhov.viboranet.profile.main

import stepan.gorokhov.viboranet.coreui.models.StableImage
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent

sealed class MainProfileEvent : UIEvent {
    data object LoadProfile : MainProfileEvent()
    data class UpdateAvatar(val image: StableImage) : MainProfileEvent()
    data class UpdateUsername(val username: String) : MainProfileEvent()
    data object LoadMoreTests : MainProfileEvent()
    data class NavigateEditTest(val testId: String) : MainProfileEvent()
} 