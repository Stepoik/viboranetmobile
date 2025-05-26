package stepan.gorokhov.viboranet.chat

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.chat.api.ChatRepository
import stepan.gorokhov.viboranet.chat.data.ChatRepositoryImpl
import stepan.gorokhov.viboranet.chat.data.WebsocketChatConnection
import stepan.gorokhov.viboranet.coredata.di.AUTHORIZED_KTOR_CLIENT_QUALIFIER

val chatModule = module {
    factory { WebsocketChatConnection(get(AUTHORIZED_KTOR_CLIENT_QUALIFIER)) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }

    viewModel { ChatViewModel() }
}