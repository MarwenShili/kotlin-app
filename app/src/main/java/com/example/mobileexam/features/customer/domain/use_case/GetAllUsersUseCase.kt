package com.example.mobileexam.features.customer.domain.use_case

import android.util.Log
import com.example.mobileexam.core.utils.Resource
import com.example.mobileexam.features.customer.domain.models.User
import com.example.mobileexam.features.customer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllUsersUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<User>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = userRepository.getAllUsers()
                emit(Resource.Success(response))
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                Log.e("onEvent", "GetAllUsersUseCase: ${throwable.message}")
                emit(Resource.Error(message = ""))
            }
        }
    }
}