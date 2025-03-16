package org.mrdevv.eyealert.evaluation.data

import androidx.compose.runtime.Composable
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
import org.mrdevv.eyealert.evaluation.model.dto.CrearEvaluacionDTO
import org.mrdevv.eyealert.evaluation.model.dto.RequestRespuestasCuestionario
import org.mrdevv.eyealert.evaluation.model.dto.ResponseDetailEvaluacion
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionByUser
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionDTO
import org.mrdevv.eyealert.evaluation.model.dto.ResponseNivelRiesgo
import org.mrdevv.eyealert.evaluation.model.usecase.IEvaluacion
import org.mrdevv.eyealert.network.HttpClient

class EvaluacionImpl : IEvaluacion {
    override fun getEvaluacionesByUser(
        idUser: Long,
        onResponse: (ResponseEvaluacionByUser?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/usuarios/$idUser/evaluaciones"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseEvaluacionByUser>()
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

    override fun getLastEvaluacionesByUser(
        idUser: Long,
        onResponse: (ResponseEvaluacionByUser?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/usuarios/$idUser/evaluaciones/latest"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseEvaluacionByUser>()
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

    override fun getLastWeekEvaluacionesByUser(
        idUser: Long,
        onResponse: (ResponseEvaluacionByUser?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/usuarios/$idUser/evaluaciones/latest-seven-days"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseEvaluacionByUser>()
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

    override fun getLastMonthEvaluacionesByUser(
        idUser: Long,
        onResponse: (ResponseEvaluacionByUser?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/usuarios/$idUser/evaluaciones/last-month"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseEvaluacionByUser>()
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

    override fun getDetailEvaluacion(
        idEvaluacion: Long,
        onResponse: (ResponseDetailEvaluacion?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/detalleEvaluacion/$idEvaluacion"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseDetailEvaluacion>()
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

    override fun getNivelRiesgoEvaluacion(
        respuestasCuestionario: RequestRespuestasCuestionario,
        onResponse: (ResponseNivelRiesgo?) -> Unit
    ) {
        val url = "http://192.168.1.4:4000/evaluation"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.post(url){
                    contentType(ContentType.Application.Json)
                    setBody(respuestasCuestionario)
                }.body<ResponseNivelRiesgo>()

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

    override fun crearEvaluacion(
        evaluacionDTO: CrearEvaluacionDTO,
        onResponse: (ResponseEvaluacionDTO?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/evaluaciones"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.post(url){
                    contentType(ContentType.Application.Json)
                    setBody(evaluacionDTO)
                }.body<ResponseEvaluacionDTO>()

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