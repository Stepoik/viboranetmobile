package stepan.gorokhov.viboranet.profile.data

import dev.gitlive.firebase.auth.FirebaseUser
import stepan.gorokhov.viboranet.profile.api.models.User

fun FirebaseUser.toDomain(): User {
    return User(
        id = uid,
        name = displayName ?: ""
    )
}