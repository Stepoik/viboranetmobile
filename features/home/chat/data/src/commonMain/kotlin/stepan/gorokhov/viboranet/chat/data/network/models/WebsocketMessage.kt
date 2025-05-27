package stepan.gorokhov.viboranet.chat.data.network.models

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import stepan.gorokhov.viboranet.chat.api.models.Message
import stepan.gorokhov.viboranet.chat.api.models.MessageAuthor
import stepan.gorokhov.viboranet.core.uuid.Uuid

@Serializable
data class WebsocketMessage(
    @SerialName("content")
    val content: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("username")
    val username: String,
    @SerialName("timestamp")
    val timestamp: String
)

fun WebsocketMessage.toMessage(): Message {
    return Message(
        id = Uuid.generate(),
        text = content,
        author = MessageAuthor(
            id = userId,
            name = username
        ),
        timestamp = Instant.parse(timestamp).toLocalDateTime(TimeZone.currentSystemDefault())
    )
}
