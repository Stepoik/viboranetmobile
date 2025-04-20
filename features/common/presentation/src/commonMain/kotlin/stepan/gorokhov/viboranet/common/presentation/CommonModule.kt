package stepan.gorokhov.viboranet.common.presentation

import org.koin.dsl.module
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.common.data.UserRepositoryImpl

val commonModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}