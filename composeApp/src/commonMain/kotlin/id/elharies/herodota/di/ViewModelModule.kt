package id.elharies.herodota.di

import id.elharies.herodota.ui.detail.DetailViewModel
import id.elharies.herodota.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { DetailViewModel(get()) }
}