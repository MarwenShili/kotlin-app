package com.example.mobileexam.features.customer.presentation.all_customers_screen

import com.example.mobileexam.features.customer.domain.models.User


data class AllCustomersState(
    val isLoading: Boolean = false,
    val error: String = "",
    val query: String = "",
    val filteredUsers: List<User> = emptyList(),
    val users: List<User> = emptyList(),
)