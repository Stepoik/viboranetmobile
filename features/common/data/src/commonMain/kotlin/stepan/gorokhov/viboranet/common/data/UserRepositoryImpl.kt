package stepan.gorokhov.viboranet.common.data

import stepan.gorokhov.viboranet.common.api.models.User
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository

class UserRepositoryImpl : UserRepository {
    override suspend fun updateUser(): Result<Any?> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUser(): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun clearUser(): Result<Any?> {
        TODO("Not yet implemented")
    }
}