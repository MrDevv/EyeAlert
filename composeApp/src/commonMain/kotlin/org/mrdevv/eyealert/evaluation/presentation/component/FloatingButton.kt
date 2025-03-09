package org.mrdevv.eyealert.evaluation.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.evaluation.model.domain.Pregunta

@Composable
fun FloatingButton(
    listPreguntas: List<Pregunta>,
    listState: LazyListState,
    coroutineScope: CoroutineScope
) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    println(listPreguntas.size)
                    listState.animateScrollToItem(listPreguntas.lastIndex)
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            containerColor = Color(0xFF224164)
        ) {
            Icon(
                Icons.Default.ArrowDownward,
                contentDescription = "Ir abajo",
                tint = Color.White
            )
        }
    }
}