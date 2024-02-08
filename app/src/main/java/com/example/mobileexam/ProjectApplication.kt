package com.example.mobileexam

import android.app.Application
import com.example.mobileexam.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ProjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
            androidLogger()
        }
    }
}