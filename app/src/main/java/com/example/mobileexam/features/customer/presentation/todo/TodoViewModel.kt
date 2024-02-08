package com.example.mobileexam.features.customer.presentation.todo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileexam.core.utils.Resource
import com.example.mobileexam.features.customer.domain.use_case.DeleteUserByIdUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetAllUsersUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetTodosUseCase
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(TodoState())
    val state: State<TodoState> = _state

    init {
        getAllTodos()
    }

    fun onEvent(event: TodoEvent) {
        when (event) {
            TodoEvent.GetAllTodos -> {
                getAllTodos()
            }
        }
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            getTodosUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        Log.e("onEvent", "Get All Todos: ${result.data}")
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            todos = result.data ?: emptyList()
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
}