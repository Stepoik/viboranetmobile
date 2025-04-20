package stepan.gorokhov.viboranet.auth.presentation.signup

import org.jetbrains.compose.resources.StringResource
import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.coreui.validation.ValidationError
import viboranet.features.auth.presentation.generated.resources.Res
import viboranet.features.auth.presentation.generated.resources.email_incorrect
import viboranet.features.auth.presentation.generated.resources.incorrect_credentials
import viboranet.features.auth.presentation.generated.resources.passwords_dont_match
import viboranet.features.auth.presentation.generated.resources.simple_password

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val isSinging: Boolean = false,
    val error: SignUpError? = null
) : UIState, ViewModelState<SignUpState> {
    override fun toScreenState() = this
}

sealed class SignUpEffect : UIEffect {
    data object NavigateHome : SignUpEffect()
    data object NavigateSignIn : SignUpEffect()
}

sealed class SignUpEvent : UIEvent {
    data class SetEmail(val email: String) : SignUpEvent()
    data class SetPassword(val password: String) : SignUpEvent()
    data class SetPasswordConfirmation(val password: String) : SignUpEvent()
    data object SignUpClicked : SignUpEvent()
    data object NavigateSignInClicked : SignUpEvent()
}

sealed class SignUpError(val text: StringResource) : ValidationError {
    class IncorrectEmail : SignUpError(Res.string.email_incorrect)
    class SimplePassword : SignUpError(Res.string.simple_password)
    class MismatchedPasswords : SignUpError(Res.string.passwords_dont_match)
    class IncorrectCredentials : SignUpError(Res.string.incorrect_credentials)
}
