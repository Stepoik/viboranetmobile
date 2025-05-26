package stepan.gorokhov.viboranet.chat.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import stepan.gorokhov.viboranet.chat.data.network.models.MessageDto
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants

private const val BASE_MESSAGES_URL = "${NetworkConstants.BASE_URL}/messages"

class MessageApi(
    private val httpClient: HttpClient
) {
    suspend fun getMessages(offset: Long, limit: Long): List<MessageDto> {
        return httpClient.get(BASE_MESSAGES_URL) {
            parameter("offset", offset)
            parameter("limit", limit)
        }.body()
    }
}