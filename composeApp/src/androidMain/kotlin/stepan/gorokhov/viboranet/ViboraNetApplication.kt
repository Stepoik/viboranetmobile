package stepan.gorokhov.viboranet

import android.app.Application
import android.content.Context
import org.koin.dsl.module
import stepan.gorokhov.viboranet.root.startDiContainer

class ViboraNetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startDiContainer(getApplicationModule())
    }
}

private fun ViboraNetApplication.getApplicationModule() = module {
    single<Context> { this@getApplicationModule }
}