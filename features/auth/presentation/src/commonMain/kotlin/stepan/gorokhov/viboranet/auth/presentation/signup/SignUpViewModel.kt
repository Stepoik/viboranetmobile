package stepan.gorokhov.viboranet.auth.presentation.signup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.auth.api.models.SignUpCredentials
import stepan.gorokhov.viboranet.auth.domain.SignUpUseCase
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.coreui.validation.getErrorOrNull

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignUpState, SignUpState, SignUpEffect, SignUpEvent>() {
    override fun getInitialState(): SignUpState {
        return SignUpState()
    }

    override fun sendEvent(event: SignUpEvent) {
        viewModelScope.launch {
            when (event) {
                is SignUpEvent.SetEmail -> setEmail(event.email)
                is SignUpEvent.SetPassword -> setPassword(event.password)
                is SignUpEvent.SetPasswordConfirmation -> setPasswordConfirmation(event.password)
                is SignUpEvent.SignUpClicked -> signUp()
                is SignUpEvent.NavigateSignInClicked -> navigateSignIn()
            }
        }
    }

    private fun setEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun setPassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun setPasswordConfirmation(password: String) {
        _state.update { it.copy(passwordConfirmation = password) }
    }

    private suspend fun signUp() {
        val state = _state.value
        if (state.isSinging) return

        val validationResult = state.validateCredentials()
        validationResult.getErrorOrNull()?.let { error ->
            _state.update { it.copy(error = error) }
            return
        }

        signUpUseCase.execute(state.toSignUpCredentials()).onFailure {
            _state.update { it.copy(error = SignUpError.IncorrectCredentials()) }
        }.onSuccess {
            _effect.emit(SignUpEffect.NavigateHome)
        }
    }

    private suspend fun navigateSignIn() {
        _effect.emit(SignUpEffect.NavigateSignIn)
    }
}

private fun SignUpState.toSignUpCredentials(): SignUpCredentials {
    return SignUpCredentials(
        login = email,
        password = password
    )
}