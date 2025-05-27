package stepan.gorokhov.viboranet.root

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import org.koin.dsl.module
import stepan.gorokhov.viboranet.auth.presentation.di.authModule
import stepan.gorokhov.viboranet.common.presentation.commonModule
import stepan.gorokhov.viboranet.coredata.di.coreDataModule
import stepan.gorokhov.viboranet.coredata.network.TokenHolder
import stepan.gorokhov.viboranet.coreui.di.coreUiModule
import stepan.gorokhov.viboranet.database.databaseModule
import stepan.gorokhov.viboranet.home.flow.homeModule
import stepan.gorokhov.viboranet.splash.splashModule

val rootModule = module {
    single { Firebase.auth }
    includes(
        coreDataModule,
        authModule,
        homeModule,
        splashModule,
        databaseModule,
        commonModule,
        coreUiModule
    )
}