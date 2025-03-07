package org.mrdevv.eyealert.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun Name(name: String, onNameChange: (String) -> Unit) {
    TextField(
        name,
        onValueChange = { onNameChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Nombres",
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}