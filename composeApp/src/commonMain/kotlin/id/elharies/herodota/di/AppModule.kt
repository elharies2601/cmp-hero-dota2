package id.elharies.herodota.di

import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

fun appModule() = startKoin {
    modules(networkModule, repositoryModule, viewModelModule)
}
//fun appModule() = koinApplication {
//    modules(networkModule, repositoryModule, viewModelModule)
//}