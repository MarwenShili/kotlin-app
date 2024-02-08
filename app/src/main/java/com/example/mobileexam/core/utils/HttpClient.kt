package com.example.mobileexam.core.utils

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
fun createHttpClient(
): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 6000
            requestTimeoutMillis = 8000
        }

        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                println("HTTP status: $statusCode")
                println("response: $response")
                when (statusCode) {
                    !in 200..299 -> {
                        throw ServerResponseException(
                            response,
                            "ServerResponseException"
                        )
                    }
                }
            }

            handleResponseExceptionWithRequest { cause: Throwable, _ ->
                throw cause
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}