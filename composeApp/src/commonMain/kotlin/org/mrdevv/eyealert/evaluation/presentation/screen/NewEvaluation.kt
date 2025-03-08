package org.mrdevv.eyealert.evaluation.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.mrdevv.eyealert.evaluation.data.PreguntasImpl
import org.mrdevv.eyealert.evaluation.model.domain.Pregunta

public class NewEvaluation : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow


        val preguntasImpl = PreguntasImpl();

        var listPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }

        LaunchedEffect(Unit) {
            preguntasImpl.getListPreguntas { response ->
                //            println("response preguntas ${response?.code}")
                if (response?.code == 200) {
                    listPreguntas = response.data!!
                }
                println("preguntas ${listPreguntas}")
            }
        }

        LazyColumn(
            Modifier.fillMaxSize().background(Color(0xFFF6F6F6))
        ) {
            stickyHeader {
                Row(
                    Modifier.fillMaxSize().background(Color.White).padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Nueva Evaluación",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        content = {
                            Icon(Icons.Default.Close, contentDescription = "")
                        },
                        onClick = {
                            navigator.pop()
                        }
                    )
                }
            }


            itemsIndexed(listPreguntas) { index, pregunta ->
                var selectedOption by rememberSaveable { mutableStateOf<String?>(null) } // Estado para la selección
                var valueInput by remember { mutableStateOf("") }


                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        Modifier.fillParentMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(text = "${index + 1}. ${pregunta.descripcion}", color = Color.Black)
                        Row {
                            if (pregunta.listRespuestas.isNotEmpty()){
                                pregunta.listRespuestas.forEach { respuesta ->
                                    Row(
                                        Modifier.fillParentMaxWidth().clickable {
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
                            }else{
                                Column {
                                    TextField(
                                        value =  valueInput,
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
            }
        }
    }

}