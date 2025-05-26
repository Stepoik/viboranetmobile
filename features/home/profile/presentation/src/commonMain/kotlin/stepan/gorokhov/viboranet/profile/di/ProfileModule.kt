package stepan.gorokhov.viboranet.profile.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.profile.api.ProfileRepository
import stepan.gorokhov.viboranet.profile.data.ProfileRepositoryImpl
import stepan.gorokhov.viboranet.profile.main.MainProfileViewModel

val profileModule = module {
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }

    viewModel { MainProfileViewModel() }
}