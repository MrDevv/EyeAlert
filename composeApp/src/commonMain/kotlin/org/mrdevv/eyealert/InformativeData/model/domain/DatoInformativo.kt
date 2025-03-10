package org.mrdevv.eyealert.InformativeData.model.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatoInformativo (
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val fuente: String,
    @SerialName("fuente_multimedia")
    val fuenteMultimedia: String
)