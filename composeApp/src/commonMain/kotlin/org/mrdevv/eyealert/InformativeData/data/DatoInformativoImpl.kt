package org.mrdevv.eyealert.InformativeData.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo
import org.mrdevv.eyealert.InformativeData.model.dto.ResponseDatoInformativo
import org.mrdevv.eyealert.InformativeData.model.dto.ResponseDatosInformativos
import org.mrdevv.eyealert.InformativeData.model.usecase.IDatoInformativo
import org.mrdevv.eyealert.network.HttpClient
import org.mrdevv.eyealert.utils.Constants

class DatoInformativoImpl : IDatoInformativo {
    override fun getTresDatosInformativosAleatorios(onResponse: (ResponseDatosInformativos?) -> Unit) {
        val url = "${Constants.BASE_URL}api/v1/datosInformativos/aleatorios"

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

    override fun getAllDatosInformativos(onResponse: (ResponseDatosInformativos?) -> Unit) {
        val url = "${Constants.BASE_URL}api/v1/datosInformativos"

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

    override fun getDatoInformativoAleatorio(onResponse: (ResponseDatoInformativo?) -> Unit) {
        val url = "${Constants.BASE_URL}api/v1/datosInformativos/aleatorio"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = HttpClient.httpClient.get(url).body<ResponseDatoInformativo>()
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