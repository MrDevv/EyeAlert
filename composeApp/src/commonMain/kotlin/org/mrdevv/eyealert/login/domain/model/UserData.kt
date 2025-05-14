package org.mrdevv.eyealert.login.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData (
    val id: Long,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val rol: String,
    val cuestionarioCompleado: Boolean,
    val fecha: String
)