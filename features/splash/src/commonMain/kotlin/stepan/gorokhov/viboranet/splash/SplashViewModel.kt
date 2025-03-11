package stepan.gorokhov.viboranet.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel

class SplashViewModel : BaseViewModel<SplashState, SplashState, SplashEffect, SplashEvent>() {
    override fun getInitialState() = SplashState

    override fun sendEvent(event: SplashEvent) {
        viewModelScope.launch {
            when (event) {
                SplashEvent.CheckUser -> checkUserAuth()
            }
        }
    }

    private suspend fun checkUserAuth() {

    }
}