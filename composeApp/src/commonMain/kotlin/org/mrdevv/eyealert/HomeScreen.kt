package org.mrdevv.eyealert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1976DF), Color(0xFF0C4D96)),
                )
            )
    ) {
        Text("Home")
    }
}