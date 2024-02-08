package com.example.mobileexam.features.customer.data.repository

import com.example.mobileexam.features.customer.data.local.UserLocalDataSource
import com.example.mobileexam.features.customer.data.mapper.toUser
import com.example.mobileexam.features.customer.data.mapper.toUserEntity
import com.example.mobileexam.features.customer.data.remote.RemoteDataSource
import com.example.mobileexam.features.customer.domain.models.Todo
import com.example.mobileexam.features.customer.domain.models.User
import com.example.mobileexam.features.customer.domain.repository.UserRepository
import java.util.UUID

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : UserRepository {
    override suspend fun getTodos(): List<Todo> {
        return try {
            remoteDataSource.getTodos().todos
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getAllUsers(): List<User> {
        return userLocalDataSource.getAllUsers().map { it.toUser() }
    }

    override suspend fun addUser(user: User) {
        userLocalDataSource.insertUser(user.toUserEntity())
    }

    override suspend fun getUserByIdUseCase(id: UUID): User {
        return userLocalDataSource.getUserById(id).toUser()
    }

    override suspend fun deleteUserByIdUseCase(id: UUID) {
        userLocalDataSource.deleteUserById(id)
    }

}