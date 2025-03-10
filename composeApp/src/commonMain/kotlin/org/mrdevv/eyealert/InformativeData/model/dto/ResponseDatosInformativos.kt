package org.mrdevv.eyealert.InformativeData.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo

@Serializable
data class ResponseDatosInformativos(
    @SerialName("data")
    val data: List<DatoInformativo>,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)