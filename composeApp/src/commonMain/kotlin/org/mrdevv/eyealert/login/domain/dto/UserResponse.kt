package org.mrdevv.eyealert.login.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mrdevv.eyealert.login.domain.model.UserData

@Serializable
data class UserResponse(
    @SerialName("data")
    val userData: UserData?,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int
//    val listUsers: List<UserData>
)
