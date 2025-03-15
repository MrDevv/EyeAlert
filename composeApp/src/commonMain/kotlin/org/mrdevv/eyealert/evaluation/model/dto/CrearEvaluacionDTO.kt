package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrearEvaluacionDTO(
    @SerialName("tiempo_prediccion")
    val tiempoPrediccion : Double,
    @SerialName("resultado")
    val resultado: Int,
    @SerialName("usuario_id")
    val usuarioId: Long,
    @SerialName("detalle_evaluacion")
    val detalleEvuacion: List<CrearDetalleEvaluacionDTO>
)
