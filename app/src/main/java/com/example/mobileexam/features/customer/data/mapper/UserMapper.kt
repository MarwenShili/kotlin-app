package com.example.mobileexam.features.customer.data.mapper

import android.util.Log
import com.example.mobileexam.features.customer.data.local.entities.UserEntity
import com.example.mobileexam.features.customer.domain.models.User

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        lastName = lastName,
        birthDate = birthDate,
        email = email,
        phoneNumber = phoneNumber
    )
}

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        lastName = lastName,
        birthDate = birthDate,
        email = email,
        phoneNumber = phoneNumber
    )
}
