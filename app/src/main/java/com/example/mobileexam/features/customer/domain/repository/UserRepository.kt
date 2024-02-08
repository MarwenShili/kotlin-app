package com.example.mobileexam.features.customer.domain.repository

import com.example.mobileexam.features.customer.domain.models.Todo
import com.example.mobileexam.features.customer.domain.models.User
import java.util.UUID

interface UserRepository {
    suspend fun getTodos(): List<Todo>
    suspend fun getAllUsers(): List<User>
    suspend fun addUser(user: User)
    suspend fun getUserByIdUseCase(id: UUID): User?
    suspend fun deleteUserByIdUseCase(id: UUID)
}