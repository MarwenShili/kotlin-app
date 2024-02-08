package com.example.mobileexam.core.di

import androidx.lifecycle.SavedStateHandle
import com.example.mobileexam.core.db.ProjectDatabase
import com.example.mobileexam.features.customer.data.local.UserDao
import com.example.mobileexam.features.customer.data.local.UserLocalDataSource
import com.example.mobileexam.features.customer.data.remote.RemoteDataSource
import com.example.mobileexam.features.customer.data.remote.TodoApi
import com.example.mobileexam.features.customer.data.repository.UserRepositoryImpl
import com.example.mobileexam.features.customer.domain.repository.UserRepository
import com.example.mobileexam.features.customer.domain.use_case.AddCustomerUseCase
import com.example.mobileexam.features.customer.domain.use_case.DeleteUserByIdUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetAllUsersUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetTodosUseCase
import com.example.mobileexam.features.customer.domain.use_case.GetUserByIdUseCase
import com.example.mobileexam.features.customer.presentation.add_edit_customer.AddEditCustomersViewModel
import com.example.mobileexam.features.customer.presentation.all_customers_screen.AllCustomersViewModel
import com.example.mobileexam.features.customer.presentation.todo.TodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    single<UserDao> {
        val database = get<ProjectDatabase>()
        database.userDao
    }

    single { TodoApi(httpClient = get()) }
    single { UserLocalDataSource(dao = get()) }
    single { RemoteDataSource(todoApi = get()) }

    single<UserRepository> {
        UserRepositoryImpl(
            userLocalDataSource = get(),
            remoteDataSource = get(),
        )
    }

    single {
        GetAllUsersUseCase(userRepository = get())
    }
    single {
        AddCustomerUseCase(userRepository = get())
    }
    single {
        GetUserByIdUseCase(userRepository = get())
    }
    single {
        DeleteUserByIdUseCase(userRepository = get())
    }
    single {
        GetTodosUseCase(userRepository = get())
    }
    viewModel { (savedStateHandle: SavedStateHandle) ->
        AllCustomersViewModel(getAllUsersUseCase = get(), deleteUserByIdUseCase = get())
    }
    viewModel { (savedStateHandle: SavedStateHandle) ->
        AddEditCustomersViewModel(addCustomerUseCase = get(), getUserByIdUseCase = get())
    }
    viewModel { (savedStateHandle: SavedStateHandle) ->
        TodoViewModel(getTodosUseCase = get())
    }
}