package org.mrdevv.eyealert.recoverPassword.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mrdevv.eyealert.login.data.AuthProviderImpl
import org.mrdevv.eyealert.ui.components.DividerFormsAuth
import org.mrdevv.eyealert.ui.components.Email

@Composable
fun RecoverPassword(isRecoverPassoword: (Boolean) -> Unit) {
    var email by remember { mutableStateOf("") }


    var isValidEmail by remember { mutableStateOf(true) }

    val authProvider = AuthProviderImpl()

    val keyboardController = LocalSoftwareKeyboardController.current

    var isEnableButton by remember { mutableStateOf(false) }

    var isCodeReceived by remember { mutableStateOf(false) }

    if (email.isNotEmpty() && isValidEmail){
        isEnableButton = true
    }else{
        isEnableButton = false
    }

    if (!isCodeReceived){
        Text("RECUPERAR CONTRASEÑA", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text("Por favor ingresa el correo electrónico asociado a tu cuenta, para poder enviarte un código.")
        Spacer(Modifier.height(10.dp))
        Email(email, isValidEmail, onValidEmailChange = { isValidEmail = it }) { email = it }

        Spacer(Modifier.height(20.dp))

        OutlinedButton(modifier = Modifier.fillMaxWidth(),
            enabled = isEnableButton,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976DF),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF76B6FF)
            ),
            border = BorderStroke(2.dp, Color.Transparent),
            onClick = {
                isCodeReceived = true
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("ENVIAR CÓDIGO", fontWeight = FontWeight.Bold)
            }
        }

        DividerFormsAuth()

        OutlinedButton(modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFF1976DF),
            ),
            border = BorderStroke(2.dp, Color(0xFF1976DF)),
            onClick = {
                isRecoverPassoword(false)
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("IR A INICIAR SESIÓN", fontWeight = FontWeight.Bold)
            }
        }
    }else{
        FormConfirmCode(email, isRecoverPassoword)
    }
}