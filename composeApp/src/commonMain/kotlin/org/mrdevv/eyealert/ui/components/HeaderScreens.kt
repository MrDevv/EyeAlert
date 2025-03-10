package org.mrdevv.eyealert.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russhwolf.settings.Settings

@Composable
fun HeaderScreens(settings: Settings) {
    Column {
        Row {
            Text("Bienvenido", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(5.dp))
            Text(settings.getString("NAME", ""), fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(Modifier.height(5.dp))
        Text(
            "Evalúa tu salud ocular y mejora tu conocimiento sobre el glaucoma",
            color = Color.White
        )
    }
}