package com.example.mobileexam.core.db

import androidx.room.TypeConverter
import com.example.mobileexam.features.customer.data.local.entities.UserEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class TypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromUserEntityToString(role: UserEntity?): String? {
        return if (role != null) Gson().toJson(role) else null
    }

    @TypeConverter
    fun fromStringToUserEntity(data: String?): UserEntity? {
        val listType = object : TypeToken<UserEntity>() {}.type
        return if (data != null) Gson().fromJson(data, listType) else null
    }

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}