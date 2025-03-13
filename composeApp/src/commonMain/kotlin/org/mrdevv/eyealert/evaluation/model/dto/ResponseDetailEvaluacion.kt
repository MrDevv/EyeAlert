package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDetailEvaluacion (
    @SerialName("data")
    val data: ResponseDataEvaluacion?,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)

@Serializable
data class ResponseDataEvaluacion(
    @SerialName("detalle_evaluacion_id")
    val detalleEvaluacionId: Long,
    @SerialName("evaluacion_id")
    val evaluacionId: Long,
    val nombres : String,
    val apellidos : String,
    val fecha : String,
    @SerialName("tiempo_prediccion")
    val tiempoPrediccion : Int,
    val resultado : String,
    @SerialName("listPreguntaRespuesta")
    val listaPreguntasRespuestas : List<ResponsePreguntaRespuesta>
)

@Serializable
data class ResponsePreguntaRespuesta(
    val pregunta: String,
    val respuesta: String
)