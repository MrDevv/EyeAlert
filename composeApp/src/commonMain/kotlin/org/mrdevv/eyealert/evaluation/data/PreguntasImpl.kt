package org.mrdevv.eyealert.evaluation.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.evaluation.model.dto.ResponsePreguntas
import org.mrdevv.eyealert.evaluation.model.usecase.IPreguntas
import org.mrdevv.eyealert.network.HttpClient

class PreguntasImpl : IPreguntas {
    override fun getListPreguntas(onResponse: (ResponsePreguntas?) -> Unit) {
        val url = "http://192.168.1.4:8080/api/v1/preguntas"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponsePreguntas>()

                onResponse(response)
            }catch (e: Exception) {
                print("Error en la api: ${e.message}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResponse(null) // Devolver null en caso de error
                }
            }
        }
    }
}