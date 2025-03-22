package org.mrdevv.eyealert.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.mrdevv.eyealert.utils.Validator

@Composable
fun Email(email: String,
          isValidEmail: Boolean,
          onValidEmailChange: (Boolean) -> Unit,
          onEmailChange: (String) -> Unit) {

    TextField(
        email,
        onValueChange = {
            onEmailChange(it)
            onValidEmailChange(Validator.validateEmail(it))
        },
        isError = !isValidEmail,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Correo electrónico",
                fontWeight = FontWeight.ExtraLight
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF464646),
            focusedContainerColor = Color(0xFFE3E3E3),
            unfocusedContainerColor = Color(0xFFE3E3E3),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "icon email")
        }
    )
    if (!isValidEmail){
        Text(modifier = Modifier.fillMaxWidth(), text = "El email no es valido", color = Color.Red, textAlign = TextAlign.Right, fontSize = 13.sp)
    }
}