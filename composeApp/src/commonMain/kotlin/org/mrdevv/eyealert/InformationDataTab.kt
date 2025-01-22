package org.mrdevv.eyealert

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object InformationDataTab: Tab {

    @Composable
    override fun Content() {
        InformationDataScreen()
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