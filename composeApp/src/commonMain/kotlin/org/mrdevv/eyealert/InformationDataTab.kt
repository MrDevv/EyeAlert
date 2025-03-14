package org.mrdevv.eyealert

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.mrdevv.eyealert.InformativeData.presentation.screen.InformationDataScreen

object InformationDataTab: Tab {

    @Composable
    override fun Content() {
        Navigator(InformationDataScreen()) { navigator ->
            SlideTransition(navigator)
        }

    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Lightbulb)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "DATOS INFORMATIVOS",
                    icon = icon
                )
            }
        }
}