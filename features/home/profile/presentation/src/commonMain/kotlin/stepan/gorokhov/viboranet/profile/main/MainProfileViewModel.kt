package stepan.gorokhov.viboranet.profile.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.common.api.models.NewUserData
import stepan.gorokhov.viboranet.common.api.models.User
import stepan.gorokhov.viboranet.common.api.repositories.ImageRepository
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.coreui.models.StableImage
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel

class MainProfileViewModel(
    private val userRepository: UserRepository,
    private val imageRepository: ImageRepository
) : BaseViewModel<MainProfileState, MainProfileViewModelState, MainProfileEffect, MainProfileEvent>() {
    override fun getInitialState(): MainProfileViewModelState = MainProfileViewModelState()

    override fun handleEvent(event: MainProfileEvent) {
        when (event) {
            is MainProfileEvent.LoadProfile -> loadProfile()
            is MainProfileEvent.UpdateAvatar -> updateUserAvatar(event.image)

            is MainProfileEvent.UpdateUsername -> updateUsername(event.username)
        }
    }

    private fun loadProfile() {
        if (_state.value.loading) return

        _state.update { it.copy(loading = true, error = null) }
        viewModelScope.launch(Dispatchers.Default) {
            userRepository.getUser().onSuccess { user ->
                setUser(user)
            }
        }
        _state.update { it.copy(loading = false) }
    }

    private fun updateUsername(username: String) {
        viewModelScope.launch(Dispatchers.Default) {
            userRepository.updateUser(
                NewUserData(
                    imageUrl = _state.value.avatarUrl,
                    username = username
                )
            ).onSuccess { user ->
                setUser(user)
            }
        }
    }

    private fun updateUserAvatar(image: StableImage) {
        viewModelScope.launch(Dispatchers.Default) {
            val imageId = imageRepository.uploadImage(image.bytes).getOrNull() ?: return@launch
            val imageUrl = "${NetworkConstants.BASE_URL}/images/$imageId"
            userRepository.updateUser(
                NewUserData(
                    imageUrl = imageUrl,
                    username = _state.value.username
                )
            ).onSuccess { user ->
                setUser(user)
            }
        }
    }

    private fun setUser(user: User) {
        _state.update { it.copy(username = user.name, avatarUrl = user.image) }
    }
} 