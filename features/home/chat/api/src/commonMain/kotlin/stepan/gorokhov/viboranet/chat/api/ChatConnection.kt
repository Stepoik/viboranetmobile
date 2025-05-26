package stepan.gorokhov.viboranet.chat.api

import kotlinx.coroutines.flow.Flow
import stepan.gorokhov.viboranet.chat.api.models.Message

interface ChatConnection {
    suspend fun subscribe(): Flow<Message>

    suspend fun sendMessage(text: String): Result<Any?>
}