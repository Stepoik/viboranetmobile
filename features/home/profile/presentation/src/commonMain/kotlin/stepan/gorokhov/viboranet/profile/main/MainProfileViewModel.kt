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
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository

class MainProfileViewModel(
    private val userRepository: UserRepository,
    private val imageRepository: ImageRepository,
    private val testRepository: TestRepository
) : BaseViewModel<MainProfileState, MainProfileViewModelState, MainProfileEffect, MainProfileEvent>() {
    override fun getInitialState(): MainProfileViewModelState = MainProfileViewModelState()

    override fun handleEvent(event: MainProfileEvent) {
        when (event) {
            is MainProfileEvent.NavigateEditTest -> _effect.tryEmit(
                MainProfileEffect.NavigateEditTest(event.testId)
            )

            is MainProfileEvent.LoadProfile -> loadProfile()
            is MainProfileEvent.UpdateAvatar -> updateUserAvatar(event.image)
            is MainProfileEvent.UpdateUsername -> updateUsername(event.username)
            is MainProfileEvent.LoadMoreTests -> loadMoreTests()
        }
    }

    private fun loadProfile() {
        if (_state.value.loading) return

        _state.update { it.copy(loading = true, error = null) }
        viewModelScope.launch(Dispatchers.Default) {
            userRepository.getUser().onSuccess { user ->
                setUser(user)
                loadCreatedTests(user.id, 0)
            }
        }
        _state.update { it.copy(loading = false) }
    }

    private suspend fun loadCreatedTests(userId: String, offset: Int) {
        testRepository.getTestsByAuthor(authorId = userId, offset = offset.toLong())
            .onSuccess { tests ->
                _state.update { currentState ->
                    currentState.copy(
                        createdTests = if (offset == 0) tests else currentState.createdTests + tests,
                        hasMoreTests = tests.isNotEmpty(),
                        currentPage = offset / 20 + 1
                    )
                }
            }
    }

    private fun loadMoreTests() {
        if (_state.value.isLoadingMore || !_state.value.hasMoreTests) return

        viewModelScope.launch(Dispatchers.Default) {
            _state.update { it.copy(isLoadingMore = true) }
            userRepository.getUser().onSuccess { user ->
                val offset = _state.value.currentPage * 20
                loadCreatedTests(user.id, offset)
            }
            _state.update { it.copy(isLoadingMore = false) }
        }
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