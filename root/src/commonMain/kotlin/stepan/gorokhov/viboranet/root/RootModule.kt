package stepan.gorokhov.viboranet.root

import org.koin.dsl.module
import stepan.gorokhov.viboranet.auth.presentation.di.authModule
import stepan.gorokhov.viboranet.common.presentation.commonModule
import stepan.gorokhov.viboranet.coredata.di.coreDataModule
import stepan.gorokhov.viboranet.database.databaseModule
import stepan.gorokhov.viboranet.home.flow.homeModule
import stepan.gorokhov.viboranet.splash.splashModule

val rootModule = module {
    includes(
        coreDataModule,
        authModule,
        homeModule,
        splashModule,
        databaseModule,
        commonModule
    )
}