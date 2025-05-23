package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseNivelRiesgo (
    val status: Int,
    @SerialName("result_evaluation")
    val resultEvaluation: String,
    val message: String,
    @SerialName("start_time")
    val tiempoPredicionInicio: String,
    @SerialName("end_time")
    val tiempoPredicionFin: String,
    @SerialName("prediction_time_ms")
    val predictionTimeMs: Double
)