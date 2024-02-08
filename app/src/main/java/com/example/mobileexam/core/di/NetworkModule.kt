package com.example.mobileexam.core.di

import com.example.mobileexam.core.utils.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module
    get() = { enableLogging ->
        module {
            single { createHttpClient() }
        }
    }