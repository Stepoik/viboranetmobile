package stepan.gorokhov.viboranet.tests.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.coredata.di.AUTHORIZED_KTOR_CLIENT_QUALIFIER
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository
import stepan.gorokhov.viboranet.tests.api.repositories.TestResultRepository
import stepan.gorokhov.viboranet.tests.data.preview.TestRepositoryImpl
import stepan.gorokhov.viboranet.tests.data.preview.TestResultRepositoryImpl
import stepan.gorokhov.viboranet.tests.data.preview.network.ResultApi
import stepan.gorokhov.viboranet.tests.data.preview.network.TestApi
import stepan.gorokhov.viboranet.tests.domain.ongoingTest.OngoingTestInteractor
import stepan.gorokhov.viboranet.tests.presentation.createTest.CreateTestViewModel
import stepan.gorokhov.viboranet.tests.presentation.main.MainTestsViewModel
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.OngoingTestScreen
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.OngoingTestViewModel
import stepan.gorokhov.viboranet.tests.presentation.testResult.TestResultViewModel
import stepan.gorokhov.viboranet.tests.presentation.testpreview.TestPreviewViewModel

val testsModule = module {
    single { TestApi(get(AUTHORIZED_KTOR_CLIENT_QUALIFIER)) }
    single { ResultApi(get(AUTHORIZED_KTOR_CLIENT_QUALIFIER)) }

    single<TestResultRepository> { TestResultRepositoryImpl(get()) }
    single<TestRepository> { TestRepositoryImpl(get()) }

    factory { OngoingTestInteractor(get(), get()) }

    viewModel { MainTestsViewModel(get()) }
    viewModel { OngoingTestViewModel(get(), get()) }
    viewModel { CreateTestViewModel(get(), get()) }
    viewModel { TestPreviewViewModel(get(), get()) }
    viewModel { TestResultViewModel(get(), get()) }
}