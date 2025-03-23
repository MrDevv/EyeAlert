package org.mrdevv.eyealert.InformativeData.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo
import org.mrdevv.eyealert.utils.limitText

@Composable
fun CardInformativeData(infomativeData: DatoInformativo, selectedInformativeData: (DatoInformativo) -> Unit) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(110.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6DB2FF)
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            selectedInformativeData(infomativeData)
        }
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Row(
                    modifier = Modifier.fillMaxWidth().weight(2.6f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = limitText(
                            infomativeData.descripcion,
                            12
                        ),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = Color(0xFFB2FF59),
                    modifier = Modifier.size(32.dp).weight(1f)
                )
            }
        }
    }
}