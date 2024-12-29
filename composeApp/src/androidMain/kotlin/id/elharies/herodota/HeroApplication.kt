package id.elharies.herodota

import android.app.Application
import id.elharies.herodota.di.appModule
import org.koin.core.context.stopKoin

class HeroApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        stopKoin()
        appModule()
    }
}