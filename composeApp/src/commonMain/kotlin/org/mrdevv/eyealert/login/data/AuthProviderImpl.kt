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

class AuthProviderImpl : AuthProvider{
    override fun signIn(email: String, password: String, onResponse: (UserResponse?) -> Unit) {
        val url = "http://192.168.1.4:8080/api/v1/usuarios/auth"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(UserRequest(email, password))
                }.body<UserResponse>()
//                println("respuesta API: $response")

//                val userData = response.userData


                onResponse(response)
            } catch (e: Exception) {
                print("Error en la api: ${e.message}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResponse(null) // Devolver null en caso de error
                }
            }
        }
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser() {
        TODO("Not yet implemented")
    }
}