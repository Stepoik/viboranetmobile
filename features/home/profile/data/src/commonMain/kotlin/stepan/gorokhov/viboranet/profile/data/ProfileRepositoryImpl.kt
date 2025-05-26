package stepan.gorokhov.viboranet.profile.data

import dev.gitlive.firebase.auth.FirebaseAuth
import stepan.gorokhov.viboranet.profile.api.ProfileRepository
import stepan.gorokhov.viboranet.profile.api.models.User

class ProfileRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
): ProfileRepository {
    override suspend fun getCurrentUser(): Result<User> {
        return runCatching {
            firebaseAuth.currentUser!!.toDomain()
        }
    }
}