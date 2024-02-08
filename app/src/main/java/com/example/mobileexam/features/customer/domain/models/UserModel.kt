package com.example.mobileexam.features.customer.domain.models

import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val phoneNumber: String
)