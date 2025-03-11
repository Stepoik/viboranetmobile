package stepan.gorokhov.viboranet.coredata.network

import io.ktor.client.HttpClient

class TokenRefresher(
    private val httpClient: HttpClient,
) {
    suspend fun refreshTokens(refreshToken: String): BearerTokens? {
        TODO("")
    }
}