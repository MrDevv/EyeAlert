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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mrdevv.eyealert.ui.components.DividerFormsAuth
import org.mrdevv.eyealert.ui.components.Password

@Composable
fun FormConfirmCode(email: String, isRecoverPassoword: (Boolean) -> Unit){
    var code by remember { mutableStateOf("") }

    var isEnableButton by remember { mutableStateOf(false) }
    var isCodeCorrect by remember { mutableStateOf(false) }

    if (code.isNotEmpty() && code.length == 6){
        isEnableButton = true
    }else{
        isEnableButton = false
    }

    if (!isCodeCorrect){
        Text("COMPRUEBA TU CORREO ELECTRÓNICO", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text("Hemos enviado un código a $email, verifica tu bandeja de entrada o spam. Introdúcelo para confirmar tu cuenta.")
        Spacer(Modifier.height(10.dp))

        TextField(
            placeholder = {
                Text(
                    "ejemplo: 892012",
                    fontWeight = FontWeight.ExtraLight
                )
            },
            value = code,
            onValueChange = { input ->
                if (input.all { char -> char.isDigit() }) {
                    code = input
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF464646),
                focusedContainerColor = Color(0xFFE3E3E3),
                unfocusedContainerColor = Color(0xFFE3E3E3),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Tag, contentDescription = "icon email")
            }
        )

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
                isCodeCorrect = true
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("VERIFICAR", fontWeight = FontWeight.Bold)
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
        var password by remember { mutableStateOf("") }

        Text("REESTABLECER CONTRASEÑA", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text("Por favor ingresa tu nueva contraseña.")
        Spacer(Modifier.height(10.dp))
        Password(password) { password = it }
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
                isCodeCorrect = true
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("CAMBIAR CONTRASEÑA", fontWeight = FontWeight.Bold)
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
    }

}