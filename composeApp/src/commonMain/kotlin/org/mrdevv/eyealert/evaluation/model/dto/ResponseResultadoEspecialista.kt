package org.mrdevv.eyealert.evaluation.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.recoverPassword.model.dto.CodeResponse

@Serializable
data class ResponseResultadoEspecialista (
    @SerialName("data")
    val data: CodeResponse? = null,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)

@Serializable
data class ResultadoEspecialista(
    @SerialName("resultado_especialista")
    val resultadoEspecialista: Boolean
)