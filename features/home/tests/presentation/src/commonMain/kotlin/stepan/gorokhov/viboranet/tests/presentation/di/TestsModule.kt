package stepan.gorokhov.viboranet.tests.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository
import stepan.gorokhov.viboranet.tests.data.preview.TestRepositoryImpl
import stepan.gorokhov.viboranet.tests.domain.ongoingTest.OngoingTestInteractor
import stepan.gorokhov.viboranet.tests.presentation.main.MainTestsViewModel
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.OngoingTestScreen
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.OngoingTestViewModel

val testsModule = module {
    single<TestRepository> { TestRepositoryImpl() }
    factory { OngoingTestInteractor(get()) }

    viewModel { MainTestsViewModel(get()) }
    viewModel { OngoingTestViewModel(get(), get()) }
}