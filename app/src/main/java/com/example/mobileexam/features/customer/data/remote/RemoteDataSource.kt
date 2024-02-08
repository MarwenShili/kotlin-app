package com.example.mobileexam.features.customer.data.remote

import com.example.mobileexam.features.customer.data.remote.responses.TodoResponse

class RemoteDataSource(private var todoApi: TodoApi) {
    suspend fun getTodos(): TodoResponse {
        return todoApi.getTodos()
    }
}