package org.mrdevv.eyealert.recoverPassword.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.evaluation.model.dto.DetailUserAndEvaluations

@Serializable
data class ResponseCodeToEmail(
    @SerialName("data")
    val data: CodeResponse? = null,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)

@Serializable
data class CodeResponse(
    val code: Int,
    @SerialName("usuario_id")
    val usuarioId: Long
)
