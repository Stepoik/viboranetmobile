package stepan.gorokhov.viboranet.profile.main

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect

sealed class MainProfileEffect : UIEffect {
    data class ShowError(val message: String) : MainProfileEffect()
    data class NavigateEditTest(val testId: String) : MainProfileEffect()
} 