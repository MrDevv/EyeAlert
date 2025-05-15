package org.mrdevv.eyealert.recoverPassword.data

import io.ktor.client.call.body
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionDTO
import org.mrdevv.eyealert.network.HttpClient
import org.mrdevv.eyealert.recoverPassword.model.dto.PasswordDTO
import org.mrdevv.eyealert.recoverPassword.model.dto.ResponseCodeToEmail
import org.mrdevv.eyealert.recoverPassword.model.dto.ResponseResetPassword
import org.mrdevv.eyealert.recoverPassword.model.dto.SendEmail
import org.mrdevv.eyealert.recoverPassword.model.usecase.IRecoverPassword
import org.mrdevv.eyealert.utils.Constants

class RecoverPasswordImpl : IRecoverPassword {
    override fun sendCodeToEmail(correo: SendEmail, onResponse: (ResponseCodeToEmail?) -> Unit) {
        val url = "${Constants.BASE_URL}api/v1/usuarios/recover-password"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.post(url){
                    contentType(ContentType.Application.Json)
                    setBody(correo)
                }.body<ResponseCodeToEmail>()

                onResponse(response)

            } catch (e: Exception) {
                println("Error en la api: ${e.message}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResponse(null)
                }
            }
        }
    }

    override fun resetPassword(
        newPassword: PasswordDTO,
        usuarioId: Int,
        onResponse: (ResponseResetPassword?) -> Unit
    ) {
        val url = "${Constants.BASE_URL}api/v1/usuarios/$usuarioId/reset-password"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.patch(url) {
                    contentType(ContentType.Application.Json)
                    setBody(newPassword)
                }.body<ResponseResetPassword>()

                onResponse(response)

            } catch (e: Exception) {
                println("Error en la api: ${e.message}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResponse(null)
                }
            }
        }
    }
}