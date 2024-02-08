package com.example.mobileexam.features.customer.presentation.all_customers_screen


sealed class AllCustomersEvent {
    object GetAllUsers : AllCustomersEvent()
    class DeleteUser(val id: String) : AllCustomersEvent()
    class Search(val query: String) : AllCustomersEvent()
}