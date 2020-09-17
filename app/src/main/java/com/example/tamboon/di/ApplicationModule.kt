package com.example.tamboon.di

import com.example.tamboon.data.domain.GetCharitiesUseCase
import com.example.tamboon.data.domain.ValidateCreditCardUseCase
import com.example.tamboon.data.repository.TamBoonRepository
import com.example.tamboon.data.repository.TamBoonRepositoryImpl
import com.example.tamboon.ui.charities.CharitiesViewModel
import com.example.tamboon.ui.donation.DonationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel {
        CharitiesViewModel(get())
    }
    viewModel {
        DonationViewModel(get())
    }
}

val repositoryModule: Module = module {
    single<TamBoonRepository> { TamBoonRepositoryImpl(get()) }
}

val useCaseModule: Module = module {
    single { GetCharitiesUseCase(get()) }
    single { ValidateCreditCardUseCase() }
}

val networkModule = module {
    single { provideGson() }
    single { provideApi(get()) }
}

val applicationModule = listOf(networkModule, viewModelModule, useCaseModule, repositoryModule)