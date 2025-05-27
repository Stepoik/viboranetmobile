package stepan.gorokhov.viboranet.chat

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import stepan.gorokhov.viboranet.chat.api.models.Message

private val formatter = LocalDateTime.Format {
    hour()
    char(':')
    minute()
}

fun Message.toVO(userId: String): MessageVO {
    return MessageVO(
        id = id,
        text = text,
        isFromMe = author.id == userId,
        time = timestamp.format(formatter),
        author = AuthorVO(
            username = author.name
        )
    )
}