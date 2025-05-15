package org.mrdevv.eyealert.recoverPassword.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendEmail (
    val email: String
)