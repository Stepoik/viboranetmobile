package stepan.gorokhov.viboranet.splash

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState

data object SplashState : UIState, ViewModelState<SplashState> {
    override fun toScreenState() = this
}

sealed class SplashEffect : UIEffect {
    data object NavigateAuth : SplashEffect()
    data object NavigateTests : SplashEffect()
}

sealed class SplashEvent : UIEvent {
    data object CheckUser : SplashEvent()
}