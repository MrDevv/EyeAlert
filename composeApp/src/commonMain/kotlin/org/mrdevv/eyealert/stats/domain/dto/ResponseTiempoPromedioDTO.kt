package org.mrdevv.eyealert.stats.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTiempoPromedioDTO(
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: TiempoPromedio
)

@Serializable
data class TiempoPromedio(
    @SerialName("total_evaluaciones")
    val totalEvaluaciones: Int,
    @SerialName("tiempo_promedio_prediccion")
    val tiempoPromedioPrediccion: Double
)
