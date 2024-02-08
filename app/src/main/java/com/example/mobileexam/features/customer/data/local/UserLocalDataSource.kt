package com.example.mobileexam.features.customer.data.local

import com.example.mobileexam.features.customer.data.local.entities.UserEntity
import java.util.UUID

class UserLocalDataSource(private var dao: UserDao) {
    suspend fun insertUser(tagEntity: UserEntity) {
        return dao.insertUser(tagEntity)
    }

    suspend fun getAllUsers(): List<UserEntity> {
        return dao.getAllUsers()
    }

    suspend fun getUserById(id: UUID): UserEntity {
        return dao.getUserById(id)
    }

    suspend fun deleteUserById(id: UUID) {
        return dao.deleteUserById(id)
    }

}