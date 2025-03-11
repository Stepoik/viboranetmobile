package stepan.gorokhov.viboranet.auth.api.repositories

import stepan.gorokhov.viboranet.auth.api.models.SignInCredentials
import stepan.gorokhov.viboranet.auth.api.models.SignUpCredentials

interface AuthRepository {
    suspend fun signIn(credentials: SignInCredentials): Result<Any?>

    suspend fun signUp(credentials: SignUpCredentials): Result<Any?>

    suspend fun logout(): Result<Any?>
}