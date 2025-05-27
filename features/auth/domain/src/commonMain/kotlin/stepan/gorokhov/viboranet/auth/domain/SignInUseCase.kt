package stepan.gorokhov.viboranet.auth.domain

import stepan.gorokhov.viboranet.auth.api.models.SignInCredentials
import stepan.gorokhov.viboranet.auth.api.repositories.AuthRepository
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository

class SignInUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(signInCredentials: SignInCredentials): Result<Any?> {
        authRepository.signIn(signInCredentials).onFailure { return Result.failure(it) }
        return userRepository.refreshUser()
    }
}