package org.mrdevv.eyealert.stats.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTasaCiertoDTO (
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: TasaAcierto
)

@Serializable
data class TasaAcierto(
    @SerialName("total_evaluaciones")
    val totalEvaluaciones: Int,
    @SerialName("evaluaciones_acertadas")
    val evaluacionesAcertadas: Int,
    @SerialName("evaluaciones_no_acertadas")
    val evaluacionesNoAcertadas: Int,
    @SerialName("evaluaciones_pendientes_revision")
    val evaluacionesPendientesRevision: Int,
    @SerialName("tasa_acierto")
    val tasaAcierto: Double,

)