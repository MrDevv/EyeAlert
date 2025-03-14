package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestRespuestasCuestionario (
    @SerialName("age")
    val edad: Int,
    @SerialName("sex")
    val genero: Int,
    @SerialName("iop")
    val PIOElevada: Int,
    @SerialName("familyHistory")
    val historialFamiliar: Int,
    @SerialName("diabetes")
    val diabetes: Int,
    @SerialName("hypertension")
    val hipertensionArterial: Int,
    @SerialName("cataractStatus")
    val catarata: Int
)