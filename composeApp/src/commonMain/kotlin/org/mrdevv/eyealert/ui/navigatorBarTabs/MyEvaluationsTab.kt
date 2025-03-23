package org.mrdevv.eyealert.ui.navigatorBarTabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.mrdevv.eyealert.evaluation.presentation.screen.MyEvaluationsScreen

object MyEvaluationsTab: Tab {

    @Composable
    override fun Content() {
        Navigator(MyEvaluationsScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.History)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "MIS EVALUACIONES",
                    icon = icon
                )
            }
        }

}