package stepan.gorokhov.viboranet.chat.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NewMessage(
    val content: String
)
