package org.mrdevv.eyealert.stats.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.network.HttpClient
import org.mrdevv.eyealert.stats.domain.dto.ResponseIndiceConocimientoDTO
import org.mrdevv.eyealert.stats.domain.dto.ResponseTasaCiertoDTO
import org.mrdevv.eyealert.stats.domain.dto.ResponseTiempoPromedioDTO
import org.mrdevv.eyealert.stats.domain.usecase.IStats
import org.mrdevv.eyealert.utils.Constants

class StatsImpl : IStats {
    override fun obtenerTasaAcierto(onResponse: (ResponseTasaCiertoDTO?) -> Unit) {
        val url = "${Constants.BASE_URL}api/v1/evaluaciones/tasa-acierto"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url){
                    contentType(ContentType.Application.Json)
                }.body<ResponseTasaCiertoDTO>()

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

    override fun obtenerTiempoPromedio(onResponse: (ResponseTiempoPromedioDTO?) -> Unit) {
        val url = "${Constants.BASE_URL}api/v1/evaluaciones/tiempo-promedio-prediccion"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url){
                    contentType(ContentType.Application.Json)
                }.body<ResponseTiempoPromedioDTO>()

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

    override fun obtenerIndiceConocimiento(onResponse: (ResponseIndiceConocimientoDTO?) -> Unit) {
        val url = "${Constants.BASE_URL}api/v1/cuestionarioConocimientos/indice-conocimiento"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url){
                    contentType(ContentType.Application.Json)
                }.body<ResponseIndiceConocimientoDTO>()

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