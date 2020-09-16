package com.example.tamboon.di

import com.example.tamboon.ui.charitylist.CharityListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel {
        CharityListViewModel()
    }
}

val useCaseModule: Module = module {

}

val networkModule = module {
    single { provideGson() }
    single { provideApi(get()) }
}

val applicationModule = listOf(networkModule, viewModelModule, useCaseModule)