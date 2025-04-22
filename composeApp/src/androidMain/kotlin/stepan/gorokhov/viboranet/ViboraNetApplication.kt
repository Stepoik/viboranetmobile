package stepan.gorokhov.viboranet

import android.app.Application
import android.content.Context
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.koin.dsl.module
import stepan.gorokhov.viboranet.root.startDiContainer

class ViboraNetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startDiContainer(getApplicationModule())
        Firebase.initialize(this)
    }
}

private fun ViboraNetApplication.getApplicationModule() = module {
    single<Context> { this@getApplicationModule }
}