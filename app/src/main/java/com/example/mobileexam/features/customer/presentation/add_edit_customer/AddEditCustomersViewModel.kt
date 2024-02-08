package com.example.mobileexam.features.customer.presentation.add_edit_customer

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileexam.core.utils.Resource
import com.example.mobileexam.features.customer.domain.models.User
import com.example.mobileexam.features.customer.domain.use_case.AddCustomerUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetAllUsersUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetUserByIdUseCase
import kotlinx.coroutines.launch
import java.util.UUID

class AddEditCustomersViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val addCustomerUseCase: AddCustomerUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AddEditCustomersState())
    val state: State<AddEditCustomersState> = _state

    fun onEvent(event: AddEditCustomersEvent) {
        when (event) {
            is AddEditCustomersEvent.AddCustomer -> {
                viewModelScope.launch {
                    val user = User(
                        id = UUID.randomUUID(),
                        name = state.value.name,
                        lastName = state.value.lastName,
                        email = state.value.email,
                        phoneNumber = state.value.phoneNumber,
                        birthDate = state.value.birthDate,
                    )
                    addCustomerUseCase.invoke(user).collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _state.value = state.value.copy(
                                    isLoading = true,
                                    error = ""
                                )
                            }

                            is Resource.Success -> {
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    error = "",
                                    success = true
                                )
                            }

                            is Resource.Error -> {
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    error = result.message!!,
                                )
                            }
                        }
                    }
                }
            }
            is AddEditCustomersEvent.EditCustomer -> {
                viewModelScope.launch {
                    val user = User(
                        id = UUID.fromString(event.id),
                        name = state.value.name,
                        lastName = state.value.lastName,
                        email = state.value.email,
                        phoneNumber = state.value.phoneNumber,
                        birthDate = state.value.birthDate,
                    )
                    addCustomerUseCase.invoke(user).collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _state.value = state.value.copy(
                                    isLoading = true,
                                    error = ""
                                )
                            }

                            is Resource.Success -> {
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    error = "",
                                    success = true
                                )
                            }

                            is Resource.Error -> {
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    error = result.message!!,
                                )
                            }
                        }
                    }
                }
            }
            is AddEditCustomersEvent.GetCustomerById -> {
                viewModelScope.launch {
                    getUserByIdUseCase.invoke(UUID.fromString(event.id)).collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _state.value = state.value.copy(
                                    isLoading = true,
                                    error = ""
                                )
                            }

                            is Resource.Success -> {
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    error = "",
                                    name = result.data?.name ?: "",
                                    lastName = result.data?.lastName ?: "",
                                    email = result.data?.email ?: "",
                                    phoneNumber = result.data?.phoneNumber ?: "",
                                    birthDate = result.data?.birthDate ?: "",
                                )
                            }

                            is Resource.Error -> {
                                Log.e("onEvent", "Get User: ${result.message}")
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    error = result.message!!,
                                )
                            }
                        }
                    }
                }
            }

            is AddEditCustomersEvent.OnBirthDateChange -> {
                _state.value = state.value.copy(
                    birthDate = event.value
                )
            }
            is AddEditCustomersEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }
            is AddEditCustomersEvent.OnLastNameChange -> {
                _state.value = state.value.copy(
                    lastName = event.value
                )
            }
            is AddEditCustomersEvent.OnNameChange -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }
            is AddEditCustomersEvent.OnPhoneNumberChange -> {
                _state.value = state.value.copy(
                    phoneNumber = event.value
                )
            }
        }
    }
}