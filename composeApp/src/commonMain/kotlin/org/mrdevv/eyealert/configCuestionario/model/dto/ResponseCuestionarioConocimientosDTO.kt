package org.mrdevv.eyealert.configCuestionario.model.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion

@Serializable
data class ResponseCuestionarioConocimientosDTO(
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val cuestionario: CuestionarioConocimientos
)


@Serializable
data class CuestionarioConocimientos(
    val id: Long,
    val fecha: String,
    val usuario: String?,
    @SerialName("puntaje_total")
    val puntajeTotal: Int
)
