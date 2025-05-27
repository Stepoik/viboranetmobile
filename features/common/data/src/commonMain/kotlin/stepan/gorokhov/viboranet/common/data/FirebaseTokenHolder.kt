package stepan.gorokhov.viboranet.common.data

import dev.gitlive.firebase.auth.FirebaseAuth
import stepan.gorokhov.viboranet.coredata.network.BearerTokens
import stepan.gorokhov.viboranet.coredata.network.TokenHolder

class FirebaseTokenHolder(
    private val firebaseAuth: FirebaseAuth
) : TokenHolder {
    override suspend fun getTokens(): BearerTokens? {
        val token = firebaseAuth.currentUser?.getIdToken(false)
        return token?.let {
            BearerTokens(token, token)
        }
    }
}