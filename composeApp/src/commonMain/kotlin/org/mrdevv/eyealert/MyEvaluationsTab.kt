package org.mrdevv.eyealert

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object MyEvaluationsTab: Tab {

    @Composable
    override fun Content() {
        MyEvaluationsScreen()
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Refresh)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "MIS EVALUACIONES",
                    icon = icon
                )
            }
        }

}