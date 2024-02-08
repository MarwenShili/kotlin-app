package com.example.mobileexam.features.customer.data.local

import androidx.room.*
import com.example.mobileexam.features.customer.data.local.entities.UserEntity
import java.util.UUID

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(tagEntity: UserEntity)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM user_table WHERE id == :id")
    suspend fun getUserById(id: UUID): UserEntity

    @Query("DELETE FROM user_table WHERE id == :id")
    suspend fun deleteUserById(id: UUID)

}