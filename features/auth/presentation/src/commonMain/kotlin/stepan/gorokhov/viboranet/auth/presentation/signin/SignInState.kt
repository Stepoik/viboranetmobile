package stepan.gorokhov.viboranet.auth.presentation.signin

import org.jetbrains.compose.resources.StringResource
import stepan.gorokhov.viboranet.auth.presentation.signup.SignUpError
import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.coreui.validation.ValidationError
import viboranet.features.auth.presentation.generated.resources.Res
import viboranet.features.auth.presentation.generated.resources.email_incorrect
import viboranet.features.auth.presentation.generated.resources.incorrect_credentials

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isSinging: Boolean = false,
    val error: SignInError? = null
) : UIState, ViewModelState<SignInState> {
    override fun toScreenState() = this
}

sealed class SignInEffect : UIEffect {
    data object NavigateBack : SignInEffect()
    data object NavigateHome : SignInEffect()
}

sealed class SignInEvent : UIEvent {
    data class SetEmail(val email: String) : SignInEvent()
    data class SetPassword(val password: String) : SignInEvent()
    data object SignInClicked : SignInEvent()
    data object BackButtonClicked : SignInEvent()
}

sealed class SignInError(val text: StringResource) : ValidationError {
    class IncorrectCredentials : SignInError(Res.string.incorrect_credentials)
    class IncorrectEmail : SignInError(Res.string.email_incorrect)
}
