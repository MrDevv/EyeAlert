package org.mrdevv.eyealert.login.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest (
    val email: String,
    val password: String
)