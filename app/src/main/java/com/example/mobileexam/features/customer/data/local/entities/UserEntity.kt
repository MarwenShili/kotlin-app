package com.example.mobileexam.features.customer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val name: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val phoneNumber: String
)

