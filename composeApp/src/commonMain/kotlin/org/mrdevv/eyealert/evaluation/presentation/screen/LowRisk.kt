package org.mrdevv.eyealert.evaluation.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class LowRisk : Screen {

    @Composable
    override fun Content() {
        Text("Riesgo bajo")
    }
}