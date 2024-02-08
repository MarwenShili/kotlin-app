package com.example.mobileexam.features.customer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,
    val todo: String,
    val completed: Boolean,
)
