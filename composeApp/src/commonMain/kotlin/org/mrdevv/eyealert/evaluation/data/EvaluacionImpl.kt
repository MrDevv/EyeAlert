package org.mrdevv.eyealert.evaluation.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.evaluation.model.dto.ResponseEvaluacionByUser
import org.mrdevv.eyealert.evaluation.model.usecase.IEvaluacion
import org.mrdevv.eyealert.network.HttpClient

class EvaluacionImpl : IEvaluacion {
    override fun getEvaluacionesByUser(
        idUser: Long,
        onResponse: (ResponseEvaluacionByUser?) -> Unit
    ) {
        val url = "http://192.168.1.4:8080/api/v1/evaluaciones/$idUser"

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
        val url = "http://192.168.1.4:8080/api/v1/evaluaciones/$idUser/last"

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
        val url = "http://192.168.1.4:8080/api/v1/evaluaciones/$idUser/lastWeek"

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
}