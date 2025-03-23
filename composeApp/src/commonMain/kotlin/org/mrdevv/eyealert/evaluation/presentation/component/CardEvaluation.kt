package org.mrdevv.eyealert.evaluation.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion

@Composable
fun CardEvaluation(
    evaluacion: Evaluacion,
    colorBox: Int,
    iconBox: ImageVector,
    textBox: String,
    fecha: String,
    selectedEvaluacion: (Long) -> Unit
) {
    Card(
        onClick = {
            selectedEvaluacion(evaluacion.id)
        }
    ) {
        Row(
            Modifier.clip(RoundedCornerShape(6.dp)).fillMaxWidth()
                .background(Color(colorBox)).padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    iconBox,
                    contentDescription = "icon evaluation",
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = textBox,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(fecha, color = Color.White)
                Spacer(Modifier.height(4.dp))
                Row {
                    Text(
                        "Tiempo:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        "${evaluacion.tiempoPredicion} ms",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
                Spacer(Modifier.height(4.dp))
                Icon(
                    Icons.Filled.PostAdd,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}