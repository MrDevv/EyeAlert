package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrearDetalleEvaluacionDTO(
    @SerialName("pregunta_id")
    val preguntaId: Long,
    @SerialName("respuesta_id")
    val respuestaId: Long?,
    @SerialName("respuesta_texto")
    val respuestaTexto: String
)
