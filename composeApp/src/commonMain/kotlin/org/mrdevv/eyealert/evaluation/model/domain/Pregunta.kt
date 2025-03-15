package org.mrdevv.eyealert.evaluation.model.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pregunta(
    val id: Long,
    @SerialName("pregunta")
    val descripcion: String,
    @SerialName("respuestas")
    val listRespuestas: List<Respuesta>
)

@Serializable
data class Respuesta(
    @SerialName("respuesta_id")
    val id: Long,
    var respuesta: String
)