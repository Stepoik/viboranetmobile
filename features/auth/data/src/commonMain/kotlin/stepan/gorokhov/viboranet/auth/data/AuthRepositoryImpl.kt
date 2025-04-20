package stepan.gorokhov.viboranet.auth.data

import stepan.gorokhov.viboranet.auth.api.models.SignInCredentials
import stepan.gorokhov.viboranet.auth.api.models.SignUpCredentials
import stepan.gorokhov.viboranet.auth.api.repositories.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signIn(credentials: SignInCredentials): Result<Any?> {
        return Result.success(Unit)
    }

    override suspend fun signUp(credentials: SignUpCredentials): Result<Any?> {
        return Result.success(Unit)
    }

    override suspend fun logout(): Result<Any?> {
        return Result.success(Unit)
    }
}