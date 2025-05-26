package stepan.gorokhov.viboranet.chat.data

import stepan.gorokhov.viboranet.chat.api.ChatRepository
import stepan.gorokhov.viboranet.chat.api.models.Message
import stepan.gorokhov.viboranet.chat.data.network.MessageApi
import stepan.gorokhov.viboranet.chat.data.network.models.toMessage

class ChatRepositoryImpl(
    private val messageApi: MessageApi
): ChatRepository {
    override suspend fun getMessages(offset: Long): Result<List<Message>> {
        return runCatching {
            messageApi.getMessages(offset = offset, limit = BASE_LIMIT).map { it.toMessage() }
        }
    }

    companion object {
        private const val BASE_LIMIT = 50L
    }
}