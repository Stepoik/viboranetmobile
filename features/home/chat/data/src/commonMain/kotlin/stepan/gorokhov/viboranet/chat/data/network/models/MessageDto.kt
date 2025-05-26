@file:JvmName("MessageDtoKt")

package stepan.gorokhov.viboranet.chat.data.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import stepan.gorokhov.viboranet.chat.api.models.Message
import stepan.gorokhov.viboranet.chat.api.models.MessageAuthor

@Serializable
data class MessageDto(
    @SerialName("id")
    val id: String,
    @SerialName("content")
    val content: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("username")
    val username: String,
    @SerialName("timestamp")
    val timestamp: LocalDateTime
)

fun MessageDto.toMessage(): Message {
    return Message(
        id = id,
        text = content,
        author = MessageAuthor(
            id = userId,
            name = username
        )
    )
}
