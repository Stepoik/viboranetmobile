package stepan.gorokhov.viboranet.auth.presentation.signin

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.auth.api.models.SignInCredentials
import stepan.gorokhov.viboranet.auth.domain.SignInUseCase
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.coreui.validation.getErrorOrNull

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInState, SignInState, SignInEffect, SignInEvent>() {
    override fun handleEvent(event: SignInEvent) {
        viewModelScope.launch {
            when (event) {
                is SignInEvent.SetEmail -> setEmail(event.email)
                is SignInEvent.SetPassword -> setPassword(event.password)
                is SignInEvent.SignInClicked -> signIn()
                is SignInEvent.BackButtonClicked -> navigateBack()
            }
        }
    }

    override fun getInitialState() = SignInState()

    private fun setEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun setPassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private suspend fun signIn() {
        val state = _state.value
        if (state.isSinging) return

        val validationResult = state.validateCredentials()
        validationResult.getErrorOrNull()?.let { error ->
            _state.update { it.copy(error = error) }
            return
        }

        signInUseCase.execute(state.toSignInCredentials()).onFailure {
            _state.update { it.copy(error = SignInError.IncorrectCredentials()) }
        }.onSuccess {
            _effect.emit(SignInEffect.NavigateHome)
        }
    }

    private suspend fun navigateBack() {
        _effect.emit(SignInEffect.NavigateBack)
    }
}

private fun SignInState.toSignInCredentials() = SignInCredentials(
    login = email,
    password = password
)