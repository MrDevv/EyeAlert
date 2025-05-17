package org.mrdevv.eyealert.stats.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseIndiceConocimientoDTO(
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: IndiceConocimiento
)

@Serializable
data class  IndiceConocimiento(
    @SerialName("puntaje_total_obtenido")
    val puntajeTotalObtenido: Int,
    @SerialName("numero_participantes")
    val numeroParticipantes: Int,
    @SerialName("puntaje_total_posible")
    val puntajeTotalPosible: Int,
    @SerialName("indice_conocimiento")
    val IndiceConocimiento: Double
)

