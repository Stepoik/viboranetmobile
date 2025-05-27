package stepan.gorokhov.viboranet.chat.api.models

import kotlinx.datetime.LocalDateTime

data class Message(
    val id: String,
    val text: String,
    val author: MessageAuthor,
    val timestamp: LocalDateTime
)

data class MessageAuthor(
    val id: String,
    val name: String
)