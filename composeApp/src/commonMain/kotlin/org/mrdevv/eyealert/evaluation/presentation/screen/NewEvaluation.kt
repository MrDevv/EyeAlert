package org.mrdevv.eyealert.evaluation.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.evaluation.data.PreguntasImpl
import org.mrdevv.eyealert.evaluation.model.domain.Pregunta
import org.mrdevv.eyealert.ui.components.BoxErrorMessage

public class NewEvaluation : Screen {


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

//        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        val preguntasImpl = PreguntasImpl();

        var listPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }

        val listState = rememberLazyListState()

        val coroutineScope = rememberCoroutineScope() // Alcance de la corrutina

        val buttonKey = "loadMoreButton" // ✅ Key única para el botón final
        var isLoading by remember { mutableStateOf(true) } // ✅ Estado para controlar el loader

        var errorMessage by remember { mutableStateOf<String?>(null) } // ✅ Estado para el mensaje de error

        val showButton by remember {
            derivedStateOf {
                listState.layoutInfo.visibleItemsInfo.any { it.key == buttonKey }.not()
            }
        }

        LaunchedEffect(Unit) {
//            delay(3000)
            preguntasImpl.getListPreguntas { response ->
                if (response != null) {
                    if (response?.code == 200) {
                        listPreguntas = response.data!!
//                        errorMessage = null
                    } else if (response?.code == 500) {
                        errorMessage =
                            "Ocurrio un error al momento de cargar las preguntas. Intentelo más tarde :("
                    }
                    isLoading = false
                } else {
                    isLoading = false
                    errorMessage = "El servidor no se encuentra disponible en estos momentos"
                }
                println("preguntas ${listPreguntas}")
            }
        }

        LazyColumn(
            Modifier.fillMaxSize().background(Color(0xFFF6F6F6)),
            state = listState
        ) {

            if (errorMessage == null){
                stickyHeader {
                    Row(
                        Modifier.fillMaxSize().background(Color.White).padding(8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        5.dp
                                    )
                                )
                                .height(30.dp)
                                .background(Color(0xFF224164)),
                            content = {
                                Icon(
                                    Icons.Filled.KeyboardDoubleArrowLeft,
                                    modifier = Modifier.size(50.dp),
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            },
                            onClick = {
                                navigator.pop()
                            }
                        )

                        Spacer(Modifier.width(30.dp))

                        Text(
                            text = "Nueva Evaluación",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }

                }
            }

            if (isLoading) {
                item {
                    Box(
                        Modifier.fillMaxWidth()
                            .height(400.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(50.dp),
                            color = Color(0xFF224164)
                        )
                    }
                }
            }

            if (errorMessage.isNullOrEmpty()) {
                itemsIndexed(listPreguntas) { index, pregunta ->
                    var selectedOption by rememberSaveable { mutableStateOf<String?>(null) } // Estado para la selección
                    var valueInput by remember { mutableStateOf("") }


                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                    ) {
                        Text(text = "${index + 1}. ${pregunta.descripcion}", color = Color.Black)
                        Row(
                            Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            if (pregunta.listRespuestas.isNotEmpty()) {
                                pregunta.listRespuestas.forEach { respuesta ->
                                    Row(
                                        Modifier.clickable {
                                            selectedOption = respuesta.respuesta
                                        },
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = respuesta.respuesta)
                                        RadioButton(
                                            selected = selectedOption == respuesta.respuesta,
                                            onClick = { selectedOption = respuesta.respuesta }
                                        )
                                    }

                                }
                            } else {
                                Column {
                                    TextField(
                                        value = valueInput,
                                        onValueChange = {
                                            if (it.all { char -> char.isDigit() }) { // Filtra solo números
                                                valueInput = it
                                            }
                                        },
                                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Number
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        BoxErrorMessage(errorMessage, navigator)
                    }
                }
            }

            if (listPreguntas.isNotEmpty()) {
                item(
                    key = buttonKey
                ) {
                    println(errorMessage)

                    TextButton(
                        modifier = Modifier.wrapContentHeight()
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 30.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF224164),
                            contentColor = Color.White
                        ),
                        onClick = {
//                            bottomSheetNavigator.show(LowRisk())
                        }
                    ) {
                        Text("Realizar evaluación")
                    }
                }

            }
        }

        if (showButton && listPreguntas.isNotEmpty()) {
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

    }

}