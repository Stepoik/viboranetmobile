package stepan.gorokhov.viboranet.auth.data

import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.EmailAuthProvider
import stepan.gorokhov.viboranet.auth.api.models.SignInCredentials

fun SignInCredentials.toAuthCreds() =
    EmailAuthProvider.credential(email = login, password = password)