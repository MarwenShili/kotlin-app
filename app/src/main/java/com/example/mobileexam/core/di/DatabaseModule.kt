package com.example.mobileexam.core.di

import androidx.room.Room
import com.example.mobileexam.core.db.ProjectDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ProjectDatabase::class.java,
            ProjectDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}