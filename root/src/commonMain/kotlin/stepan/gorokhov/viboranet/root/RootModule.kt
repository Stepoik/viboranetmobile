package stepan.gorokhov.viboranet.root

import org.koin.dsl.module
import stepan.gorokhov.viboranet.auth.presentation.di.authModule
import stepan.gorokhov.viboranet.coredata.di.coreDataModule
import stepan.gorokhov.viboranet.splash.splashModule
import stepan.gorokhov.viboranet.tests.presentation.di.testsModule

val rootModule = module {
    includes(
        coreDataModule,
        authModule,
        testsModule,
        splashModule
    )
}