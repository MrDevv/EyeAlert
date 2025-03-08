package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.evaluation.model.domain.Pregunta


@Serializable
data class ResponsePreguntas(
    @SerialName("data")
    val data: List<Pregunta>?,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)
