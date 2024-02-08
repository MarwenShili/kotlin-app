package com.example.mobileexam.features.customer.domain.use_case

import com.example.mobileexam.core.utils.Resource
import com.example.mobileexam.features.customer.domain.models.User
import com.example.mobileexam.features.customer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class DeleteUserByIdUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: UUID): Flow<Resource<Unit>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = userRepository.deleteUserByIdUseCase(id)
                emit(Resource.Success(response))
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emit(Resource.Error(message = ""))
            }
        }
    }
}