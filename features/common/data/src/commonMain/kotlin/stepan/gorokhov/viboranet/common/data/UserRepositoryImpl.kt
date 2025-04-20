package stepan.gorokhov.viboranet.common.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import stepan.gorokhov.viboranet.common.api.models.User
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.database.user.UserDao

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override val isAuthorized: Flow<Boolean>
        get() = userDao.getUserFlow().map { true }

    override suspend fun updateUser(): Result<Any?> {
        return Result.success(Unit)
    }

    override suspend fun getUser(): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun clearUser(): Result<Any?> {
        TODO("Not yet implemented")
    }
}