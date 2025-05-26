package stepan.gorokhov.viboranet.chat.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import stepan.gorokhov.viboranet.chat.api.ChatConnection
import stepan.gorokhov.viboranet.chat.api.models.Message
import stepan.gorokhov.viboranet.chat.data.network.models.MessageDto
import stepan.gorokhov.viboranet.chat.data.network.models.NewMessage
import stepan.gorokhov.viboranet.chat.data.network.models.toMessage
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants

class WebsocketChatConnection(
    private val httpClient: HttpClient
) : ChatConnection {
    private var session: WebSocketSession? = null
    private val mutex = Mutex()

    override suspend fun subscribe(): Flow<Message> {
        return flow {
            while (currentCoroutineContext().isActive) {
                runCatching {
                    mutex.withLock {
                        val currentSession = session
                        if (currentSession == null || !currentSession.isActive) {
                            session = httpClient.webSocketSession("${NetworkConstants.BASE_URL}/ws")
                        }
                    }
                    session?.let { session ->
                        for (frame in session.incoming) {
                            when (frame) {
                                is Frame.Text -> {
                                    val message =
                                        Json.decodeFromString<MessageDto>(frame.readText())
                                    emit(message.toMessage())
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }

    override suspend fun sendMessage(text: String): Result<Any?> {
        return runCatching {
            val data = Json.encodeToString(NewMessage(text))
            session!!.send(Frame.Text(data))
        }
    }
}