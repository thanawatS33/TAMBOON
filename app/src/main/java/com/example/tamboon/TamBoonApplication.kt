package com.example.tamboon

import android.app.Application
import com.example.tamboon.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TamBoonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TamBoonApplication)
            modules(applicationModule)
        }
    }

}