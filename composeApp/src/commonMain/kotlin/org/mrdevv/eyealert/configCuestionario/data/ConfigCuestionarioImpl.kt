package org.mrdevv.eyealert.configCuestionario.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.configCuestionario.model.dto.CreateCuestionarioConocimientos
import org.mrdevv.eyealert.configCuestionario.model.dto.ResponseConfigCuestionario
import org.mrdevv.eyealert.configCuestionario.model.dto.ResponseCuestionarioConocimientosDTO
import org.mrdevv.eyealert.configCuestionario.model.usecase.IConfigCuestionario
import org.mrdevv.eyealert.network.HttpClient

class ConfigCuestionarioImpl : IConfigCuestionario {
    override fun getDiasEspera(onResponse: (ResponseConfigCuestionario?) -> Unit) {
        val url = "http://192.168.1.4:8080/api/v1/cuestionarioConocimientos/dias-espera"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseConfigCuestionario>()
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

    override fun saveCuestionarioConocimientos(
        cuestionarioConocimientos: CreateCuestionarioConocimientos,
        onResponse: (ResponseCuestionarioConocimientosDTO?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/cuestionarioConocimientos"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.post(url){
                    contentType(ContentType.Application.Json)
                    setBody(cuestionarioConocimientos)
                }.body<ResponseCuestionarioConocimientosDTO>()

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
