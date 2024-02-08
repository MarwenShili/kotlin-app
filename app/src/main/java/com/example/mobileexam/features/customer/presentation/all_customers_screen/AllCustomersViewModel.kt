package com.example.mobileexam.features.customer.presentation.all_customers_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileexam.core.utils.Resource
import com.example.mobileexam.features.customer.domain.use_case.DeleteUserByIdUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetAllUsersUseCase
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

class AllCustomersViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AllCustomersState())
    val state: State<AllCustomersState> = _state

    init {
        getAllUsers()
    }

    fun onEvent(event: AllCustomersEvent) {
        when (event) {
            AllCustomersEvent.GetAllUsers -> {
                getAllUsers()
            }

            is AllCustomersEvent.DeleteUser -> {
                viewModelScope.launch {
                    deleteUserByIdUseCase.invoke(UUID.fromString(event.id)).collect { result ->
                        Log.e("onEvent", "Delete User: $result")
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
                                    users = state.value.users.filter { it.id.toString() != event.id }
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

            is AllCustomersEvent.Search -> {
                _state.value = state.value.copy(
                    query = event.query,
                    filteredUsers = state.value.users.filter {
                        it.name.lowercase(Locale.ROOT).contains(event.query) ||
                        it.lastName.lowercase(Locale.ROOT).contains(event.query) ||
                        it.email.lowercase(Locale.ROOT).contains(event.query) ||
                        it.phoneNumber.lowercase(Locale.ROOT).contains(event.query)
                    },
                )
            }
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            getAllUsersUseCase.invoke().collect { result ->
                Log.e("onEvent", "Get All User: ${result}")
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        Log.e("onEvent", "Get All User: ${result.data}")
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            users = result.data ?: emptyList(),
                            filteredUsers = result.data ?: emptyList(),
                        )
                    }

                    is Resource.Error -> {
                        Log.e("onEvent", "Get All User: ${result.message}")
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message!!,
                        )
                    }
                }
            }
        }
    }
}