package org.mrdevv.eyealert.InformativeData.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp

@Composable
fun ButtonVideo(urlFuenteVideo: String) {
    val uriHandler = LocalUriHandler.current

    Button(
        onClick = {
            uriHandler.openUri(urlFuenteVideo)
        },
        modifier = Modifier.padding(bottom = 20.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFCC3724),
            contentColor = Color.White
        ),
    ) {
        Icon(Icons.Default.PlayCircle, contentDescription = "icon play video", tint = Color.White)
        Spacer(Modifier.width(5.dp))
        Text("Ver vídeo")
    }
}