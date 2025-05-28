package stepan.gorokhov.viboranet.profile.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.profile.main.MainProfileViewModel

val profileModule = module {
    viewModel { MainProfileViewModel(get(), get(), get()) }
}