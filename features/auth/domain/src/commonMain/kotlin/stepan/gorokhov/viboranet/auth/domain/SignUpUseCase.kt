package stepan.gorokhov.viboranet.auth.domain

import stepan.gorokhov.viboranet.auth.api.models.SignInCredentials
import stepan.gorokhov.viboranet.auth.api.models.SignUpCredentials
import stepan.gorokhov.viboranet.auth.api.repositories.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val signInUseCase: SignInUseCase
) {
    suspend fun execute(signUpCredentials: SignUpCredentials): Result<Any?> {
        authRepository.signUp(signUpCredentials).onFailure { return Result.failure(it) }
        return signInUseCase.execute(signUpCredentials.toSignIn())
    }
}

private fun SignUpCredentials.toSignIn() = SignInCredentials(
    login = login,
    password = password
)