package stepan.gorokhov.viboranet.tests.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.tests.api.repositories.TestPreviewRepository
import stepan.gorokhov.viboranet.tests.data.preview.TestPreviewRepositoryImpl
import stepan.gorokhov.viboranet.tests.presentation.main.MainTestsViewModel

val testsModule = module {
    single<TestPreviewRepository> { TestPreviewRepositoryImpl() }

    viewModel { MainTestsViewModel(get()) }
}