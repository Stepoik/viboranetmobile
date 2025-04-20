package stepan.gorokhov.viboranet.auth.presentation.signin

import stepan.gorokhov.viboranet.coreui.validation.ValidationResult

fun SignInState.validateCredentials(): ValidationResult<SignInError> {
    if (email.isEmailNotValid()) {
        return ValidationResult.failed(SignInError.IncorrectEmail())
    }
    return ValidationResult.success()
}

private fun String.isEmailNotValid(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return !this.matches(emailRegex)
}