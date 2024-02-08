package com.example.mobileexam.features.customer.presentation.add_edit_customer

data class AddEditCustomersState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = "",
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val birthDate: String = ""
)