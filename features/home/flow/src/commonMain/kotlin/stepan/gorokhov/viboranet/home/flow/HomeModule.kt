package stepan.gorokhov.viboranet.home.flow

import org.koin.dsl.module
import stepan.gorokhov.viboranet.tests.presentation.di.testsModule

val homeModule = module {
    includes(testsModule)
}