package stepan.gorokhov.viboranet.chat.data.network.models

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import stepan.gorokhov.viboranet.chat.api.models.Message
import stepan.gorokhov.viboranet.chat.api.models.MessageAuthor

@Serializable
data class MessageDto(
    @SerialName("Id")
    val id: String,
    @SerialName("Content")
    val content: String,
    @SerialName("UserId")
    val userId: String,
    @SerialName("Username")
    val username: String,
    @SerialName("Timestamp")
    val timestamp: String
)

fun MessageDto.toMessage(): Message {
    return Message(
        id = id,
        text = content,
        author = MessageAuthor(
            id = userId,
            name = username
        ),
        timestamp = Instant.parse(timestamp).toLocalDateTime(TimeZone.currentSystemDefault())
    )
}
