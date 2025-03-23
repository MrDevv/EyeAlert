package org.mrdevv.eyealert.evaluation.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import com.russhwolf.settings.Settings
import kotlinx.coroutines.delay
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo
import org.mrdevv.eyealert.evaluation.data.EvaluacionImpl
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion
import org.mrdevv.eyealert.evaluation.model.dto.ResponseDataEvaluacion
import org.mrdevv.eyealert.evaluation.model.dto.ResponseDetailEvaluacion
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingButton
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingLoader
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
        var isLoadingDetail by remember { mutableStateOf(false) }

        var selectedEvaluacion by remember { mutableStateOf<Long?>(null) }

        var detailEvaluacion by remember { mutableStateOf<ResponseDataEvaluacion?>(null) }


        LaunchedEffect(selectedEvaluacion) {
            if (selectedEvaluacion != null) {
                isLoadingDetail = true
//                delay(4000)
                evaluacionImpl.getDetailEvaluacion(selectedEvaluacion!!) { response ->
                    println("response de la api: $response")
                    if (response != null) {
                        if (response.code == 200) {
                            if (response.data != null) {
                                detailEvaluacion = response.data
                                selectedEvaluacion = null
                            }
                        }
                    } else {
                        errorMessage = "El servidor no se encuentra disponible en estos momentos"
                    }
                    isLoadingDetail = false;
                }
            }

        }

        if (isLoadingDetail) {
            FloatingLoader()
        }

        detailEvaluacion?.let { data ->

            val textResult: String
            val colorResult: Int
            val fecha: String = data.fecha.split(" ")[0]
            val hora: String = data.fecha.split(" ")[1]

            if (data.resultado == "alto") {
                colorResult = 0xFFCC3724.toInt()
                textResult = "RIESGO ALTO"
            } else {
                colorResult = 0xFF0C6D40.toInt()
                textResult = "RIESGO BAJO"
            }

            ModalBottomSheet(
                onDismissRequest = { detailEvaluacion = null },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                contentWindowInsets = { WindowInsets(0, 0, 0, 0) },
                dragHandle = {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .background(Color(0xFF002249)),
                        contentAlignment = Alignment.Center
                    ) {
                        Spacer(
                            Modifier
                                .width(50.dp)
                                .height(8.dp)
                                .clip(
                                    RoundedCornerShape(4.dp)
                                )
                                .background(Color.White)
                        )
                    }
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF002249))
                        .padding(bottom = 10.dp, start = 15.dp)
                ) {
                    Text(
                        "DETALLE DE LA EVALUACIÓN",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Box(
                    Modifier.fillMaxWidth().background(Color.White)
                ) {
                    Column(
                        Modifier.padding(20.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(fecha, fontWeight = FontWeight.Bold)
                            Box(
                                Modifier.clip(RoundedCornerShape(5.dp))
                                    .background(Color(colorResult))
                            ) {
                                Text(
                                    text = textResult,
                                    modifier = Modifier.padding(5.dp),
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(Modifier.height(5.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(hora, fontWeight = FontWeight.Bold)
                        }

                        Spacer(Modifier.height(10.dp))

                        LazyColumn(Modifier.fillMaxWidth()) {
                            items(data.listaPreguntasRespuestas){ resp ->
                                if (resp.pregunta == "Ingresa tu edad"){
                                    Row {
                                        Text("Edad:")
                                        Spacer(Modifier.width(5.dp))
                                        Text(resp.respuesta, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(Modifier.height(10.dp))
                                }else if(resp.pregunta == "Selecciona tu genero"){
                                    Row {
                                        Text("Genero:")
                                        Spacer(Modifier.width(5.dp))
                                        Text(resp.respuesta, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(Modifier.height(10.dp))
                                }else{
                                    Column(Modifier.fillMaxWidth()) {
                                        Text(resp.pregunta)
                                        Spacer(Modifier.height(5.dp))
                                        Text(modifier = Modifier.fillMaxWidth(), text =  resp.respuesta,textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(Modifier.height(10.dp))
                                }




                            }
                        }

                        Spacer(Modifier.height(30.dp))
                        Button(
                            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(horizontal = 40.dp),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF224164),
                                contentColor = Color.White
                            ),
                            onClick = {
                                detailEvaluacion = null
                            }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Cerrar")
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                    }
                }

            }
        }

        LaunchedEffect(seleccion) {
//            delay(4000)
            if (seleccion == "Todos"){
                isLoading = true;
                evaluacionImpl.getEvaluacionesByUser(settings.getLong("ID", 0)) { response ->
                    if (response != null) {
                        if (response.code == 200) {
                            if (response.data != null) {
                                listEvaluations = response.data.evaluaciones
                            } else{
                                listEvaluations = emptyList()
                            }
                        } else if (response.code == 500) {
                            errorMessage =
                                "Ocurrio un error al momento de cargar todas las evaluaciones del usuario . Intentelo más tarde :("
                        }
                    } else {
                        errorMessage = "El servidor no se encuentra disponible en estos momentos"
                    }
                    isLoading = false;
                }
            }

            if (seleccion == "Últimos 7 días"){
                isLoading = true;
                evaluacionImpl.getLastWeekEvaluacionesByUser(settings.getLong("ID", 0)) { response ->
                    println("response: $response")
                    if (response != null) {
                        if (response.code == 200) {
                            if (response.data != null) {
                                listEvaluations = response.data.evaluaciones
                            }else{
                                listEvaluations = emptyList()
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

            if (seleccion == "Último mes"){
                isLoading = true;
                evaluacionImpl.getLastMonthEvaluacionesByUser(settings.getLong("ID", 0)) { response ->
                    println("response: $response")
                    if (response != null) {
                        if (response.code == 200) {
                            if (response.data != null) {
                                listEvaluations = response.data.evaluaciones
                            }else{
                                listEvaluations = emptyList()
                            }
                        } else if (response.code == 500) {
                            errorMessage =
                                "Ocurrio un error al momento de cargar las evaluaciones del último mes. Intentelo más tarde :("
                        }
                    } else {
                        errorMessage = "El servidor no se encuentra disponible en estos momentos"
                    }
                    isLoading = false;
                    println(listEvaluations)
                }
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
                            var messageEvaluationEmpty: String = ""
                            if (seleccion == "Últimos 7 días"){
                                messageEvaluationEmpty = "Aún no tienes evaluaciones en estos últimos 7 días."
                            }

                            if (seleccion == "Último mes"){
                                messageEvaluationEmpty = "Aún no tienes evaluaciones en este mes."
                            }

                            if (seleccion == "Todos"){
                                messageEvaluationEmpty = "Aún no tienes evaluaciones."
                            }

                            item {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 50.dp, bottom = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        messageEvaluationEmpty,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        } else {
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

                                Card(
                                    onClick = {
                                        selectedEvaluacion = evaluacion.id
                                    }
                                ) {
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
                                                    "${evaluacion.tiempoPredicion} ms",
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