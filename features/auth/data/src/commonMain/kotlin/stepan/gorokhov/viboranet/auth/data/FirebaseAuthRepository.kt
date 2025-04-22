package stepan.gorokhov.viboranet.auth.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import stepan.gorokhov.viboranet.auth.api.models.SignInCredentials
import stepan.gorokhov.viboranet.auth.api.models.SignUpCredentials
import stepan.gorokhov.viboranet.auth.api.repositories.AuthRepository

class FirebaseAuthRepository : AuthRepository {
    private val auth = Firebase.auth

    override suspend fun signIn(credentials: SignInCredentials): Result<Any?> {
        return runCatching {
            auth.signInWithCredential(credentials.toAuthCreds()).user!!
        }
    }

    override suspend fun signUp(credentials: SignUpCredentials): Result<Any?> {
        return runCatching {
            auth.createUserWithEmailAndPassword(email = credentials.login, password = credentials.password).user!!
        }
    }

    override suspend fun logout(): Result<Any?> {
        return runCatching {
            auth.signOut()
        }
    }
}