package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion

@Serializable
data class ResponseEvaluacionDTO(
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val evaluacion: Evaluacion
)
