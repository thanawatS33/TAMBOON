package com.example.tamboon.di

import com.example.tamboon.data.domain.GetCharitiesUseCase
import com.example.tamboon.data.repository.TamBoonRepository
import com.example.tamboon.data.repository.TamBoonRepositoryImpl
import com.example.tamboon.ui.charities.CharitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel {
        CharitiesViewModel()
    }
}

val repositoryModule: Module = module {
    single<TamBoonRepository> { TamBoonRepositoryImpl(get()) }
}

val useCaseModule: Module = module {
    single { GetCharitiesUseCase(get()) }
}

val networkModule = module {
    single { provideGson() }
    single { provideApi(get()) }
}

val applicationModule = listOf(networkModule, viewModelModule, useCaseModule, repositoryModule)