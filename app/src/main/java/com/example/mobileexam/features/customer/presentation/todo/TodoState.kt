package com.example.mobileexam.features.customer.presentation.todo

import com.example.mobileexam.features.customer.domain.models.Todo

data class TodoState(
    val isLoading: Boolean = false,
    val error: String = "",
    val todos: List<Todo> = emptyList(),
)