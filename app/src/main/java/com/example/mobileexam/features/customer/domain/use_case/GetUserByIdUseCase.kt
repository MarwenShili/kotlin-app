package com.example.mobileexam.features.customer.domain.use_case

import android.util.Log
import com.example.mobileexam.core.utils.Resource
import com.example.mobileexam.features.customer.domain.models.User
import com.example.mobileexam.features.customer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class GetUserByIdUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: UUID): Flow<Resource<User?>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = userRepository.getUserByIdUseCase(id)
                emit(Resource.Success(response))
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emit(Resource.Error(message = ""))
            }
        }
    }
}