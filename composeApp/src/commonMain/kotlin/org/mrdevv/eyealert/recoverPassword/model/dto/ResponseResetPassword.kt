package org.mrdevv.eyealert.recoverPassword.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ResponseResetPassword(
    val data: JsonElement? = null,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
)

@Serializable
data class PasswordDTO(
    @SerialName("new-password")
    val newPassword: String
)
