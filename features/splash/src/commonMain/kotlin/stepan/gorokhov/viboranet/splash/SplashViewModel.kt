package stepan.gorokhov.viboranet.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel

class SplashViewModel(
    private val userRepository: UserRepository
) : BaseViewModel<SplashState, SplashState, SplashEffect, SplashEvent>() {
    override fun getInitialState() = SplashState

    override fun handleEvent(event: SplashEvent) {
        viewModelScope.launch {
            when (event) {
                SplashEvent.CheckUser -> checkUserAuth()
            }
        }
    }

    private suspend fun checkUserAuth() {
        if (userRepository.isAuthorized.first()) {
            _effect.emit(SplashEffect.NavigateTests)
        } else {
            _effect.emit(SplashEffect.NavigateAuth)
        }
    }
}