package stepan.gorokhov.viboranet.root

import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun startDiContainer(applicationModule: Module) {
    startKoin {
        modules(applicationModule, rootModule)
    }
}