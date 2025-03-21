package org.mrdevv.eyealert.login.data

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.login.domain.dto.UserRequest
import org.mrdevv.eyealert.login.domain.dto.UserResponse
import org.mrdevv.eyealert.login.domain.usecase.AuthProvider
import org.mrdevv.eyealert.network.HttpClient
import org.mrdevv.eyealert.register.domain.dto.CreateUsuario

class AuthProviderImpl : AuthProvider{
    override fun signIn(email: String, password: String, onResponse: (UserResponse?) -> Unit) {
        val url = "http://192.168.1.4:8080/api/v1/usuarios/auth"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(UserRequest(email, password))
                }.body<UserResponse>()

                onResponse(response)
            } catch (e: Exception) {
                print("Error en la api: ${e.message}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResponse(null)
                }
            }
        }
    }

    override fun register(usuario: CreateUsuario, onResponse: (UserResponse?) -> Unit) {
        val url = "http://192.168.1.4:8080/api/v1/usuarios"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(usuario)
                }.body<UserResponse>()

                onResponse(response)
            } catch (e: Exception) {
                print("Error en la api: ${e.message}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResponse(null)
                }
            }
        }
    }
}