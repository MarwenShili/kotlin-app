package com.example.mobileexam.features.customer.presentation.todo


sealed class TodoEvent {
    object GetAllTodos : TodoEvent()
}