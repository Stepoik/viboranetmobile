package stepan.gorokhov.viboranet.common.presentation

import org.koin.dsl.module
import stepan.gorokhov.viboranet.common.api.repositories.UserRepository
import stepan.gorokhov.viboranet.common.data.FirebaseUserRepository

val commonModule = module {
    single<UserRepository> { FirebaseUserRepository() }
}