package id.elharies.herodota.di

import id.elharies.herodota.data.repository.HeroRepositoryImpl
import id.elharies.herodota.domain.HeroRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<HeroRepository>{ HeroRepositoryImpl(get()) }
}