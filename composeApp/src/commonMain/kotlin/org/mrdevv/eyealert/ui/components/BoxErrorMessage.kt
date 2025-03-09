package org.mrdevv.eyealert.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun BoxErrorMessage(errorMessage: String?, onNavigateBack: Navigator) {
    Column(
        Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = Icons.Default.WifiOff,
            contentDescription = "",
            tint = Color(0xFF224164)
        )
        Text(
            text = errorMessage!!,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        TextButton(
            modifier = Modifier.wrapContentHeight()
                .wrapContentWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF224164),
                contentColor = Color.White
            ),
            onClick = {
                onNavigateBack.pop()
            }
        ) {
            Text("Regresar al Inicio")
        }
        Spacer(Modifier.height(20.dp))
    }


}