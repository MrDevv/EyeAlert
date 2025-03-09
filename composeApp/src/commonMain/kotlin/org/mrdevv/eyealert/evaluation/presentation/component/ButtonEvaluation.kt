package org.mrdevv.eyealert.evaluation.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonEvaluation() {
    TextButton(
        modifier = Modifier.wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 30.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF224164),
            contentColor = Color.White
        ),
        onClick = {}
    ) {
        Text("Realizar evaluación")
    }
}