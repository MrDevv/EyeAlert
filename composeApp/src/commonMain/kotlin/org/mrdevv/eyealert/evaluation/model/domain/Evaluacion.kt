package org.mrdevv.eyealert.evaluation.model.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Evaluacion(
    val id: Long,
    val fecha: String,
    @SerialName("tiempo_prediccion")
    val tiempoPredicion: Double,
    val resultado: String
)