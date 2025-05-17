package org.mrdevv.eyealert.evaluation.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LowRisk(navigator: Navigator, isBottomSheetVisible: (Boolean) -> Unit) {
    ModalBottomSheet(
        onDismissRequest = { isBottomSheetVisible(false) },
        sheetState = rememberModalBottomSheetState(),
        contentWindowInsets = {
            WindowInsets(0,0,0,0,)
        },
        dragHandle = {
            Box(
                Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color(0xFF0C6D40)),
                contentAlignment = Alignment.Center
            ){
                Spacer(
                    Modifier
                    .width(50.dp)
                    .height(8.dp)
                    .clip(
                        RoundedCornerShape(4.dp)
                    )
                    .background(Color.White)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0C6D40))
                .padding(bottom = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "RIESGO DE GLAUCOMA",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Box(
            Modifier
                .fillMaxWidth()
        ) {
            Column(
                Modifier.fillMaxWidth().background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "RIESGO BAJO",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0C6D40)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(Modifier.fillMaxWidth().background(Color(0xFFEEEEEE)).padding(top = 20.dp)) {
                    Text(
                        "Mantén tu estilo de vida saludable y realiza controles periódicos",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            isBottomSheetVisible(false)
                            navigator.pop()
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 20.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF224164),
                            contentColor = Color.White
                        ),
                    ) {
                        Text("Cerrar")
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}