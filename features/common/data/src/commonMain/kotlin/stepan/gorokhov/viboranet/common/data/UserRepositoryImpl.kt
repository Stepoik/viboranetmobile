package stepan.gorokhov.viboranet.common.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import stepan.gorokhov.viboranet.common.api.models.User
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.database.user.UserDao

class FirebaseUserRepository : UserRepository {
    private val auth = Firebase.auth

    override val isAuthorized: Flow<Boolean>
        get() = auth.authStateChanged.map { it != null }

    override suspend fun updateUser(): Result<Any?> {
        return runCatching {
            val currentUser = auth.currentUser!!
            auth.updateCurrentUser(currentUser)
        }
    }

    override suspend fun getUser(): Result<User> {
        return runCatching {
            auth.currentUser!!.toDomain()
        }
    }
}