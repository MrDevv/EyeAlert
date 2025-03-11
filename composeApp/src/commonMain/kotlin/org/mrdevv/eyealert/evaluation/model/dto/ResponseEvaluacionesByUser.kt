package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion

@Serializable
data class ResponseEvaluacionByUser (
    @SerialName("data")
    val data: DetailUserAndEvaluations?,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)

@Serializable
data class DetailUserAndEvaluations (
    @SerialName("id_usuario")
    val idUsuario: Int,
    val nombre: String,
    val apellido: String,
    val evaluaciones: List<Evaluacion>
)

