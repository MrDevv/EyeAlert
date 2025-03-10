package org.mrdevv.eyealert.login.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonCreateNewAccount(onShowContentLoginChange: (Boolean) -> Unit) {
    OutlinedButton(modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF344CC1),
        ),
        border = BorderStroke(2.dp, Color(0xFF344CC1)),
        onClick = {
            onShowContentLoginChange(false)
        }
    ) {
        Text("CREAR NUEVA CUENTA", fontWeight = FontWeight.Bold)
    }
}