package org.mrdevv.eyealert.configCuestionario.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion
import org.mrdevv.eyealert.evaluation.model.dto.DetailUserAndEvaluations

@Serializable
data class ResponseConfigCuestionario (
    @SerialName("data")
    val data: DiasEspera?,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)

@Serializable
data class DiasEspera (
    val id: Long,
    @SerialName("dias_espera")
    val diasEspera: Int
)
