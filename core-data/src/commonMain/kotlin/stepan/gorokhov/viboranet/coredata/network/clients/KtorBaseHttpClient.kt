package stepan.gorokhov.viboranet.coredata.network.clients

import io.ktor.client.HttpClient
import stepan.gorokhov.viboranet.coredata.network.HttpEngineFactory

fun createBaseHttpClient(): HttpClient {
    return HttpClient(HttpEngineFactory().createEngine()) {
        commonConfig()
    }
}