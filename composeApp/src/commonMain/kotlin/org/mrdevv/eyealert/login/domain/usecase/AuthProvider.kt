package org.mrdevv.eyealert.login.domain.usecase

import org.mrdevv.eyealert.login.domain.dto.UserResponse
import org.mrdevv.eyealert.register.domain.dto.CreateUsuario

interface AuthProvider {
    fun signIn(email: String, password: String, onResponse: (UserResponse?) -> Unit)
    fun register(usuario: CreateUsuario, onResponse: (UserResponse?) -> Unit)
}