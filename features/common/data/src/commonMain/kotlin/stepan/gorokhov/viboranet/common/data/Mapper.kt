package stepan.gorokhov.viboranet.common.data

import dev.gitlive.firebase.auth.FirebaseUser
import stepan.gorokhov.viboranet.common.api.models.User

fun FirebaseUser.toDomain(): User {
    return User(
        id = uid,
        name = displayName ?: "",
        image = photoURL ?: ""
    )
}