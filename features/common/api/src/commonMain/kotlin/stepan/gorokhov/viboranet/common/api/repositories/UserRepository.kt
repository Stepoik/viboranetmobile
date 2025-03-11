package stepan.gorokhov.viboranet.common.api.repositories

import stepan.gorokhov.viboranet.common.api.models.User

interface UserRepository {
    suspend fun updateUser(): Result<Any?>

    suspend fun fetchUser(): Result<User>

    suspend fun clearUser(): Result<Any?>
}