package com.example.mobileexam.features.customer.data.remote.responses

import com.example.mobileexam.features.customer.domain.models.Todo
import kotlinx.serialization.Serializable

@Serializable
data class TodoResponse(
    val todos: List<Todo>
)
