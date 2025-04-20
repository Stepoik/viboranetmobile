package stepan.gorokhov.viboranet.common.api.repositories

import kotlinx.coroutines.flow.Flow
import stepan.gorokhov.viboranet.common.api.models.User

interface UserRepository {
    val isAuthorized: Flow<Boolean>

    suspend fun updateUser(): Result<Any?>

    suspend fun getUser(): Result<User>

    suspend fun clearUser(): Result<Any?>
}