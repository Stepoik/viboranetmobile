package stepan.gorokhov.viboranet.coredata.di

import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import stepan.gorokhov.viboranet.coredata.network.TokenHolder
import stepan.gorokhov.viboranet.coredata.network.clients.createAuthorizedHttpClient
import stepan.gorokhov.viboranet.coredata.network.clients.createBaseHttpClient

val AUTHORIZED_KTOR_CLIENT_QUALIFIER = qualifier("AUTHORIZED_KTOR_CLIENT")
val BASE_KTOR_CLIENT_QUALIFIER = qualifier("BASE_KTOR_CLIENT")

internal expect fun platformModule(): Module

val coreDataModule = module {
    factory { TokenHolder(get()) }
    single(qualifier = AUTHORIZED_KTOR_CLIENT_QUALIFIER) { createAuthorizedHttpClient(get(), get()) }
    single(qualifier = BASE_KTOR_CLIENT_QUALIFIER) { createBaseHttpClient() }

    includes(platformModule())
}