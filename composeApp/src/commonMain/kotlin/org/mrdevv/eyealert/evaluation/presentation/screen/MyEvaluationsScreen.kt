package org.mrdevv.eyealert.evaluation.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.russhwolf.settings.Settings
import kotlinx.coroutines.delay
import org.mrdevv.eyealert.evaluation.data.EvaluacionImpl
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion
import org.mrdevv.eyealert.ui.components.BoxErrorMessage
import org.mrdevv.eyealert.ui.components.HeaderScreens
import org.mrdevv.eyealert.ui.components.Loader

private val settings: Settings = Settings()

class MyEvaluationsScreen() : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        var expanded by remember { mutableStateOf(false) }
        val opciones = listOf("Todos", "Últimos 7 días", "Último mes")
        var seleccion by remember { mutableStateOf(opciones[1]) }

        val evaluacionImpl = EvaluacionImpl()

        var listEvaluations by remember { mutableStateOf<List<Evaluacion>>(emptyList()) }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(seleccion) {
//            delay(4000)
            evaluacionImpl.getLastWeekEvaluacionesByUser(settings.getLong("ID", 0)) { response ->
                println("response: $response")
                if (response != null) {
                    if (response.code == 200) {
                        if (response.data != null) {
                            listEvaluations = response.data.evaluaciones
                        }
                    } else if (response.code == 500) {
                        errorMessage =
                            "Ocurrio un error al momento de cargar las evaluaciones de la última semana. Intentelo más tarde :("
                    }
                } else {
                    errorMessage = "El servidor no se encuentra disponible en estos momentos"
                }
                isLoading = false;
                println(listEvaluations)
            }

        }

        Column(
            Modifier.fillMaxSize()
        ) {
            Box(Modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
                HeaderScreens(settings)
            }
            Spacer(Modifier.height(15.dp))

            Column(
                Modifier.fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 25.dp,
                            topEnd = 25.dp
                        )
                    )
                    .background(Color.White)
            ) {
//                Cabecera
                Row(
                    Modifier.fillMaxWidth()
                        .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Mis evaluaciones", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {

                        OutlinedTextField(
                            value = seleccion,
                            onValueChange = {},
                            readOnly = true,
                            label = {
                                Text(
                                    "Filtrar por fechas",
                                    fontSize = 12.sp,
                                    color = Color.Black
                                )
                            },
                            textStyle = TextStyle(fontSize = 13.sp),
                            trailingIcon = {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "Abrir menú")
                            },
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                                .width(150.dp)
                                .height(60.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.LightGray
                            )
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            opciones.forEach { opcion ->
                                DropdownMenuItem(
                                    text = { Text(opcion, fontSize = 13.sp) },
                                    onClick = {
                                        seleccion = opcion
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }


                if (isLoading) {
                    Loader(100)
                }


//                CARDS EVALUACIONES
                if (errorMessage.isNullOrEmpty() && !isLoading) {
                    LazyColumn(
                        Modifier.fillMaxWidth().padding(top = 10.dp, start = 60.dp, end = 60.dp)
                    ) {
                        if (listEvaluations.isEmpty()) {
                            item {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 50.dp, bottom = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Aún no tienes evaluaciones.",
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        }else{
                            items(listEvaluations) { evaluacion ->
                                var colorBox: Int
                                var textBox: String
                                var iconBox: ImageVector = Icons.Default.QuestionMark

                                var fechaFormat = evaluacion.fecha.split(" ")[0]

                                if (evaluacion.resultado == "bajo") {
                                    colorBox = 0xFF0C6D40.toInt()
                                    textBox = "RIESGO BAJO"
                                    iconBox = Icons.Filled.Mood
                                } else {
                                    colorBox = 0xFFCC3724.toInt()
                                    textBox = "RIESGO ALTO"
                                    iconBox = Icons.Filled.SentimentDissatisfied
                                }

                                Row(
                                    Modifier.clip(RoundedCornerShape(6.dp)).fillMaxWidth()
                                        .background(Color(colorBox)).padding(5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            iconBox,
                                            contentDescription = "icon evaluation",
                                            tint = Color.White,
                                            modifier = Modifier.size(50.dp)
                                        )
                                        Text(
                                            text = textBox,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }
                                    Column(horizontalAlignment = Alignment.End) {
                                        Text(fechaFormat, color = Color.White)
                                        Spacer(Modifier.height(4.dp))
                                        Row {
                                            Text(
                                                "Tiempo:",
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(Modifier.width(5.dp))
                                            Text(
                                                "${evaluacion.tiempoPredicion} segundos",
                                                fontSize = 12.sp,
                                                color = Color.White
                                            )
                                        }
                                        Spacer(Modifier.height(4.dp))
                                        Icon(
                                            Icons.Filled.PostAdd,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }
                                }

                                Spacer(Modifier.height(5.dp))

                            }
                        }

                    }
                }

                if (errorMessage != null && !isLoading) {
                    BoxErrorMessage(errorMessage, 100)
                }

            }
        }
    }

}