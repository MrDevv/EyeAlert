package org.mrdevv.eyealert.InformativeData.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo

@Serializable
data class ResponseDatoInformativo(
    @SerialName("data")
    val data: DatoInformativo,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)
