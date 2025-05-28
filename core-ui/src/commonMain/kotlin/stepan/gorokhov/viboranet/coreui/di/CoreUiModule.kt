package stepan.gorokhov.viboranet.coreui.di

import org.koin.dsl.module
import stepan.gorokhov.viboranet.coredata.di.AUTHORIZED_KTOR_CLIENT_QUALIFIER
import stepan.gorokhov.viboranet.coreui.coil.authorizedImageLoader

val coreUiModule = module {
    single(createdAtStart = true) { authorizedImageLoader(get(AUTHORIZED_KTOR_CLIENT_QUALIFIER), get()) }
}