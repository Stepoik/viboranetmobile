package stepan.gorokhov.viboranet.profile.main

import kotlinx.coroutines.flow.update
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel

class MainProfileViewModel : BaseViewModel<MainProfileState, MainProfileViewModelState, MainProfileEffect, MainProfileEvent>() {
    override fun getInitialState(): MainProfileViewModelState = MainProfileViewModelState()

    override fun handleEvent(event: MainProfileEvent) {
        when (event) {
            is MainProfileEvent.LoadProfile -> {
                // Здесь должна быть загрузка профиля
                // Например, из репозитория или API
            }
            is MainProfileEvent.UpdateAvatar -> {
                _state.update { it.copy(avatarUrl = event.avatarUrl) }
            }
            is MainProfileEvent.UpdateUsername -> {
                _state.update { it.copy(username = event.username) }
            }
        }
    }
} 