package stepan.gorokhov.viboranet.coredata.network.clients

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.auth.AuthScheme
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import stepan.gorokhov.viboranet.coredata.network.BearerTokens
import stepan.gorokhov.viboranet.coredata.network.HttpEngineFactory
import stepan.gorokhov.viboranet.coredata.network.TokenHolder
import stepan.gorokhov.viboranet.coredata.network.TokenRefresher

fun createAuthorizedHttpClient(tokenHolder: TokenHolder, refresher: TokenRefresher): HttpClient {
    return HttpClient(HttpEngineFactory().createEngine()) {
        commonConfig()
    }.apply {
        enableAuthInterceptor(tokenHolder = tokenHolder, refresher = refresher)
    }
}

private fun HttpClient.enableAuthInterceptor(tokenHolder: TokenHolder, refresher: TokenRefresher) {
    val mutex = Mutex()
    plugin(HttpSend).intercept { request ->
        val tokens = tokenHolder.getTokens()
        val accessToken = tokens?.access
            ?: throw IllegalStateException("Access token is missing. User may need to log in again.")

        request.headers {
            append(HttpHeaders.Authorization, "${AuthScheme.Bearer} $accessToken")
        }
        val firstCall = execute(request)
        if (firstCall.response.status == HttpStatusCode.Forbidden) {
            val newAccessToken = updateAndGetNewTokens(
                tokenHolder = tokenHolder,
                refresher = refresher,
                initialTokens = tokens,
                mutex = mutex
            ).access

            request.headers {
                set(HttpHeaders.Authorization, "${AuthScheme.Bearer} $newAccessToken")
            }
            return@intercept execute(request)
        }
        firstCall
    }
}

private suspend fun updateAndGetNewTokens(
    tokenHolder: TokenHolder,
    refresher: TokenRefresher,
    initialTokens: BearerTokens,
    mutex: Mutex
): BearerTokens = mutex.withLock {
    val currentToken = tokenHolder.getTokens()
    val newTokens = if (currentToken == initialTokens) {
        refresher.refreshTokens(initialTokens.refresh)
    } else {
        currentToken
    }
    tokenHolder.setTokens(newTokens)
    newTokens!!
}