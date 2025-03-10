package org.mrdevv.eyealert.InformativeData.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo

@Composable
fun ButtonMoreInformation(urlFuente: String) {
    val uriHandler = LocalUriHandler.current

    Button(
        onClick = {
            uriHandler.openUri(urlFuente)
        },
        modifier = Modifier.padding(bottom = 20.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF224164),
            contentColor = Color.White
        ),
    ) {
        Text("Consultar fuente")
    }
}