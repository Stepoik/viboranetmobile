package stepan.gorokhov.viboranet.coredata.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import stepan.gorokhov.viboranet.coredata.network.TokenScheme.ACCESS_TOKEN
import stepan.gorokhov.viboranet.coredata.network.TokenScheme.REFRESH_TOKEN

private object TokenScheme {
    val ACCESS_TOKEN = stringPreferencesKey("tokens/access_token")
    val REFRESH_TOKEN = stringPreferencesKey("tokens/refresh_token")
}

class TokenHolder(
    private val dataStore: DataStore<Preferences>
) {
    val tokens = dataStore.data.map(Preferences::getTokens)

    suspend fun getTokens(): BearerTokens? {
        return dataStore.data.first().getTokens()
    }

    suspend fun setTokens(tokens: BearerTokens?) {
        dataStore.edit {
            if (tokens == null) {
                it.remove(ACCESS_TOKEN)
                it.remove(REFRESH_TOKEN)
            } else {
                it[ACCESS_TOKEN] = tokens.access
                it[REFRESH_TOKEN] = tokens.refresh
            }
        }
    }
}

private fun Preferences.getTokens(): BearerTokens? {
    val accessToken = this[ACCESS_TOKEN] ?: return null
    val refreshToken = this[REFRESH_TOKEN] ?: return null

    return BearerTokens(access = accessToken, refresh = refreshToken)
}