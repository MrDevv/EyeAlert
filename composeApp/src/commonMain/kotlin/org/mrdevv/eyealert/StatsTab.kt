package org.mrdevv.eyealert

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object StatsTab: Tab {

    @Composable
    override fun Content() {
        StatsScreen()
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Equalizer)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "ESTADISTICAS",
                    icon = icon
                )
            }
        }

}