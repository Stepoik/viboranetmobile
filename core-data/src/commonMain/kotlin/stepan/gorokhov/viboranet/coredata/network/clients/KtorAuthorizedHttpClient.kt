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

fun createAuthorizedHttpClient(tokenHolder: TokenHolder): HttpClient {
    return HttpClient(HttpEngineFactory().createEngine()) {
        commonConfig()
    }.apply {
        enableAuthInterceptor(tokenHolder = tokenHolder)
    }
}

private fun HttpClient.enableAuthInterceptor(tokenHolder: TokenHolder) {
    plugin(HttpSend).intercept { request ->
        val tokens = tokenHolder.getTokens()
        val accessToken = tokens?.access
            ?: throw IllegalStateException("Access token is missing. User may need to log in again.")

        request.headers {
            append(HttpHeaders.Authorization, "${AuthScheme.Bearer} $accessToken")
        }
        execute(request)
    }
}
