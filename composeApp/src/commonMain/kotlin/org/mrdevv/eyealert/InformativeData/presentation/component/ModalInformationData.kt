package org.mrdevv.eyealert.InformativeData.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalInformationData(informativeData: DatoInformativo, selectedInformativeData: (DatoInformativo?) -> Unit) {
    ModalBottomSheet(
        onDismissRequest = { selectedInformativeData(null) },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        contentWindowInsets = { WindowInsets(0, 0, 0, 0) },
        dragHandle = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(Color(0xFF6DB2FF)),
                contentAlignment = Alignment.Center
            ) {
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
                .background(Color(0xFF6DB2FF))
                .padding(bottom = 10.dp, start = 15.dp)
        ) {
            Text(
                "¿Sabías qué...?",
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
                Modifier.padding(20.dp)
            ) {
                Text(text = informativeData.descripcion)

                Spacer(modifier = Modifier.height(50.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Icon(
                        modifier = Modifier.size(120.dp),
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "icon led informative data",
                        tint = Color(0xFFC1CA3D)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        if (informativeData.fuenteMultimedia?.isNotEmpty() == true) {
                            ButtonVideo(informativeData.fuenteMultimedia)
                        }
                    }
                    ButtonMoreInformation(informativeData.fuente)
                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}