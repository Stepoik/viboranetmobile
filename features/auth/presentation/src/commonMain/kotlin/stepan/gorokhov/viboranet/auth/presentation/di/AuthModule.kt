package stepan.gorokhov.viboranet.auth.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.auth.api.repositories.AuthRepository
import stepan.gorokhov.viboranet.auth.data.AuthRepositoryImpl
import stepan.gorokhov.viboranet.auth.domain.SignInUseCase
import stepan.gorokhov.viboranet.auth.domain.SignUpUseCase
import stepan.gorokhov.viboranet.auth.presentation.signin.SignInViewModel
import stepan.gorokhov.viboranet.auth.presentation.signup.SignUpViewModel

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    single { SignUpUseCase(get(), get()) }
    single { SignInUseCase(get(), get()) }

    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
}