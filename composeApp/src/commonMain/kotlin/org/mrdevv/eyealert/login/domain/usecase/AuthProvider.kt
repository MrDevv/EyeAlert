package org.mrdevv.eyealert.login.domain.usecase

import org.mrdevv.eyealert.login.domain.dto.UserResponse

interface AuthProvider {
    fun signIn(email: String, password: String, onResponse: (UserResponse?) -> Unit)
    fun signOut()
    fun getCurrentUser()
}