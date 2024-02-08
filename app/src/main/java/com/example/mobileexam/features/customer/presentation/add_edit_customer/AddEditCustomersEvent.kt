package com.example.mobileexam.features.customer.presentation.add_edit_customer

sealed class AddEditCustomersEvent {
    data class GetCustomerById(val id: String) : AddEditCustomersEvent()
    object AddCustomer : AddEditCustomersEvent()
    data class EditCustomer(val id: String) : AddEditCustomersEvent()
    data class OnNameChange(val value: String) : AddEditCustomersEvent()
    data class OnLastNameChange(val value: String) : AddEditCustomersEvent()
    data class OnEmailChange(val value: String) : AddEditCustomersEvent()
    data class OnPhoneNumberChange(val value: String) : AddEditCustomersEvent()
    data class OnBirthDateChange(val value: String) : AddEditCustomersEvent()
}