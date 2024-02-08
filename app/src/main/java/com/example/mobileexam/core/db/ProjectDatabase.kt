package com.example.mobileexam.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobileexam.features.customer.data.local.UserDao
import com.example.mobileexam.features.customer.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(TypeConverter::class)
abstract class ProjectDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "project_db"
    }
}