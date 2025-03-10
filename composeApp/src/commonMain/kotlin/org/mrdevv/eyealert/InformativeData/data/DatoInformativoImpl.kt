package org.mrdevv.eyealert.InformativeData.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.InformativeData.model.dto.ResponseDatosInformativos
import org.mrdevv.eyealert.InformativeData.model.usecase.IDatoInformativo
import org.mrdevv.eyealert.network.HttpClient

class DatoInformativoImpl : IDatoInformativo {
    override fun getTresDatosInformativosAleatorios(onResponse: (ResponseDatosInformativos?) -> Unit) {
        val url = "http://192.168.1.4:8080/api/v1/datosInformativos/aleatorio"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseDatosInformativos>()
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