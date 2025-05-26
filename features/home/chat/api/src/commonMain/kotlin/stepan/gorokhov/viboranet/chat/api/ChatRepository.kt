package stepan.gorokhov.viboranet.chat.api

import stepan.gorokhov.viboranet.chat.api.models.Message

interface ChatRepository {
    suspend fun getMessages(offset: Long): Result<List<Message>>
}