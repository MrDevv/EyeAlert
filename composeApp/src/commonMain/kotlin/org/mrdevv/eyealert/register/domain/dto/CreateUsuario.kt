package org.mrdevv.eyealert.register.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateUsuario(
    val nombres: String,
    val apellidos: String,
    val email: String,
    val password: String
)