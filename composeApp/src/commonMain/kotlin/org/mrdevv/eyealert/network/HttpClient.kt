package org.mrdevv.eyealert.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClient {
    val httpClient = HttpClient(CIO) {
        engine {
            requestTimeout = 60000 // 60 segundos
            endpoint {
                connectTimeout = 20000 // 20 segundos
                socketTimeout = 60000  // 60 segundos
            }
        }
        install(ContentNegotiation){
            json(json = Json {
                ignoreUnknownKeys = true
            },
                contentType = ContentType.Any
            )
        }
    }
}