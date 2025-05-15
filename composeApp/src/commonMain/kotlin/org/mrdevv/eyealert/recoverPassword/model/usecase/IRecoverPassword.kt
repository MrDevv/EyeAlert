package org.mrdevv.eyealert.recoverPassword.model.usecase

import org.mrdevv.eyealert.recoverPassword.model.dto.PasswordDTO
import org.mrdevv.eyealert.recoverPassword.model.dto.ResponseCodeToEmail
import org.mrdevv.eyealert.recoverPassword.model.dto.ResponseResetPassword
import org.mrdevv.eyealert.recoverPassword.model.dto.SendEmail

interface IRecoverPassword {
    fun sendCodeToEmail(correo: SendEmail, onResponse: (ResponseCodeToEmail?) -> Unit)

    fun resetPassword(newPassword: PasswordDTO, usuarioId: Int, onResponse: (ResponseResetPassword?) -> Unit)
}