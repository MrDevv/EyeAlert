package org.mrdevv.eyealert.configCuestionario.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCuestionarioConocimientos(
    @SerialName("usuario_id")
    val usuarioId: Long,
    val respuestas: List<CreateRespuestaCuestionarioConocimientos>
)


@Serializable
data class CreateRespuestaCuestionarioConocimientos(
    val pregunta: String,
    val respuesta: String,
    @SerialName("puntaje_pregunta")
    val puntajePregunta: Boolean
)