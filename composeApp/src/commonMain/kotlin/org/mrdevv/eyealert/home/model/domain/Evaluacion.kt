package org.mrdevv.eyealert.home.model.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Evaluacion(
    val id: Int,
    val fecha: String,
    @SerialName("tiempo_prediccion")
    val tiempoPredicion: Int,
    val resultado: String
)