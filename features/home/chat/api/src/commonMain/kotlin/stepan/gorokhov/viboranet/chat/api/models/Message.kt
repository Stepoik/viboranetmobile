package stepan.gorokhov.viboranet.chat.api.models

data class Message(
    val id: String,
    val text: String,
    val author: MessageAuthor
)

data class MessageAuthor(
    val id: String,
    val name: String
)