package stepan.gorokhov.viboranet.profile.api

import stepan.gorokhov.viboranet.profile.api.models.User

interface ProfileRepository {
    suspend fun getCurrentUser(): Result<User>
}