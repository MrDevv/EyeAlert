package org.mrdevv.eyealert.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DividerFormsAuth() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier.fillMaxWidth().weight(1f))
        Text(
            "ó",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Divider(modifier = Modifier.fillMaxWidth().weight(1f))
    }
}