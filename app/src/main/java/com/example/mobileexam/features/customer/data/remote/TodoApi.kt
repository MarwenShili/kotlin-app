package com.example.mobileexam.features.customer.data.remote

import com.example.mobileexam.features.customer.data.remote.responses.TodoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TodoApi(
    private val httpClient: HttpClient,
) {
    suspend fun getTodos(): TodoResponse {
        return httpClient.get("https://dummyjson.com/todos") {
            contentType(ContentType.Application.Json)
        }.body<TodoResponse>()
    }
}