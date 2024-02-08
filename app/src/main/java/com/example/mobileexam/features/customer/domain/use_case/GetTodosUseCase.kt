package com.example.mobileexam.features.customer.domain.use_case

import com.example.mobileexam.core.utils.Resource
import com.example.mobileexam.features.customer.domain.models.Todo
import com.example.mobileexam.features.customer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTodosUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Todo>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = userRepository.getTodos()
                emit(Resource.Success(response))
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emit(Resource.Error(message = ""))
            }
        }
    }
}