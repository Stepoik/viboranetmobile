package stepan.gorokhov.viboranet.common.presentation

import org.koin.dsl.module
import stepan.gorokhov.viboranet.common.api.repositories.ImageRepository
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.common.data.FirebaseTokenHolder
import stepan.gorokhov.viboranet.common.data.FirebaseUserRepository
import stepan.gorokhov.viboranet.common.data.ImageRepositoryImpl
import stepan.gorokhov.viboranet.coredata.di.AUTHORIZED_KTOR_CLIENT_QUALIFIER
import stepan.gorokhov.viboranet.coredata.di.BASE_KTOR_CLIENT_QUALIFIER
import stepan.gorokhov.viboranet.coredata.network.TokenHolder

val commonModule = module {
    factory<TokenHolder> { FirebaseTokenHolder(get()) }

    single<ImageRepository> { ImageRepositoryImpl(get(AUTHORIZED_KTOR_CLIENT_QUALIFIER)) }
    single<UserRepository> { FirebaseUserRepository() }
}