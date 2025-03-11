package stepan.gorokhov.viboranet.coredata.di

import org.koin.dsl.module
import stepan.gorokhov.viboranet.coredata.preferences.createAndroidDatastore

internal actual fun platformModule() = module {
    single { createAndroidDatastore(get()) }
}