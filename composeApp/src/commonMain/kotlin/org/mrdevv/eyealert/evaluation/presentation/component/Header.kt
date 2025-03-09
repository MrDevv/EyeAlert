package org.mrdevv.eyealert.evaluation.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun Header(navigator: Navigator) {
    Row(
        Modifier.fillMaxSize().background(Color.White).padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        5.dp
                    )
                )
                .height(30.dp)
                .background(Color(0xFF224164)),
            content = {
                Icon(
                    Icons.Filled.KeyboardDoubleArrowLeft,
                    modifier = Modifier.size(50.dp),
                    contentDescription = "",
                    tint = Color.White
                )
            },
            onClick = {
                navigator.pop()
            }
        )

        Spacer(Modifier.width(30.dp))

        Text(
            text = "Nueva Evaluación",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

    }
}
