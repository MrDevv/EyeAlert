package org.mrdevv.eyealert.evaluation.presentation.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.evaluation.data.EvaluacionImpl
import org.mrdevv.eyealert.evaluation.model.dto.ResponseDataEvaluacion
import org.mrdevv.eyealert.evaluation.model.dto.ResultadoEspecialista
import org.mrdevv.eyealert.settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDetailEvaluation(
    fecha: String,
    textResult: String,
    colorResult: Int,
    hora: String,
    detailEvaluacion: ResponseDataEvaluacion,
    onChangeDetail: (ResponseDataEvaluacion?) -> Unit
) {

//    val scrollState = rememberScrollState()

    val colorResultEspecialista: Int
    val coroutineScope = rememberCoroutineScope()
    val evaluacionImpl = EvaluacionImpl()
    var isLoadingUpdateResult by remember { mutableStateOf(false) }

    if (detailEvaluacion.resultadoEspecialista == "pendiente") {
        colorResultEspecialista = 0xFF986c24.toInt()
    } else if (detailEvaluacion.resultadoEspecialista == "acertado") {
        colorResultEspecialista = 0xFF3e981f.toInt()
    } else {
        colorResultEspecialista = 0xFFaf4534.toInt()
    }


    if (isLoadingUpdateResult) {
        FloatingLoader()
    }

    ModalBottomSheet(
        onDismissRequest = { onChangeDetail(null) },
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
                        if (settings.getString("ROL", "") == "administrador") {
                            Box(
                                Modifier.clip(RoundedCornerShape(5.dp))
                                    .background(Color(colorResultEspecialista)) // color result espec.
                            ) {
                                Text(
                                    text = detailEvaluacion.resultadoEspecialista, // texto resultado especialista
                                    modifier = Modifier.padding(5.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(10.dp))

                    if (settings.getString("ROL", "") == "administrador") {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Text("Tiempo inicio predicción:")
                                Spacer(Modifier.width(5.dp))
                                Text(
                                    detailEvaluacion.tiempoPrediccionInicio.split(" ")[1],
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(Modifier.height(5.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Text("Tiempo fin predicción:")
                                Spacer(Modifier.width(5.dp))
                                Text(
                                    detailEvaluacion.tiempoPrediccionFin.split(" ")[1],
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(Modifier.height(5.dp))


                    }

                    HorizontalDivider(Modifier.height(5.dp))

                    Spacer(Modifier.height(5.dp))


                    LazyColumn(Modifier.fillMaxWidth()) {
                        items(detailEvaluacion.listaPreguntasRespuestas) { resp ->
                            if (resp.pregunta == "Ingresa tu edad") {
                                Row {
                                    Text("Edad:")
                                    Spacer(Modifier.width(5.dp))
                                    Text(resp.respuesta, fontWeight = FontWeight.Bold)
                                }
                                Spacer(Modifier.height(10.dp))
                            } else if (resp.pregunta == "Selecciona tu genero") {
                                Row {
                                    Text("Genero:")
                                    Spacer(Modifier.width(5.dp))
                                    Text(resp.respuesta, fontWeight = FontWeight.Bold)
                                }
                                Spacer(Modifier.height(10.dp))
                            } else {
                                Column(Modifier.fillMaxWidth()) {
                                    Text(resp.pregunta)
                                    Spacer(Modifier.height(5.dp))
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = resp.respuesta,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(Modifier.height(10.dp))
                            }


                        }

                        item {
                            HorizontalDivider(Modifier.height(5.dp))
                            Spacer(Modifier.height(10.dp))
                                println(detailEvaluacion.resultadoEspecialista)

                            if (settings.getString("ROL", "") == "administrador" && detailEvaluacion.resultadoEspecialista == "pendiente") {
                                Text("¿El resultado '${textResult}' de la evaluación es correcto?", fontWeight = FontWeight.Bold)

                                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                    Button(
                                        modifier = Modifier.wrapContentHeight()
                                            .padding(horizontal = 40.dp),
                                        shape = RoundedCornerShape(5.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF0C6D40),
                                            contentColor = Color.White
                                        ),
                                        onClick = {
                                            coroutineScope.launch {
                                                isLoadingUpdateResult = true
                                                evaluacionImpl.actualizarResultadoEspecialista(
                                                    detailEvaluacion.evaluacionId,
                                                    ResultadoEspecialista(true)
                                                ){ response ->
                                                    if (response != null) {
                                                        if (response.code == 200) {
                                                            onChangeDetail(null)
                                                        }
                                                    }else{
                                                        println(response)
                                                    }

                                                    isLoadingUpdateResult = false
                                                }
                                            }
                                        }
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text("Sí")
                                        }
                                    }

                                    Button(
                                        modifier = Modifier.wrapContentHeight()
                                            .padding(horizontal = 40.dp),
                                        shape = RoundedCornerShape(5.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFCC3724),
                                            contentColor = Color.White
                                        ),
                                        onClick = {
                                            coroutineScope.launch {
                                                isLoadingUpdateResult = true
                                                evaluacionImpl.actualizarResultadoEspecialista(
                                                    detailEvaluacion.evaluacionId,
                                                    ResultadoEspecialista(false)
                                                ){ response ->
                                                    if (response != null) {
                                                        if (response.code == 200) {
                                                            onChangeDetail(null)
                                                        }
                                                    }else{
                                                        println(response)
                                                    }

                                                    isLoadingUpdateResult = false
                                                }
                                            }
                                        }
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text("No")
                                        }
                                    }

                                }

                                Spacer(Modifier.height(10.dp))

                            }
                            Button(
                                modifier = Modifier.wrapContentHeight().fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF224164),
                                    contentColor = Color.White
                                ),
                                onClick = {
                                    onChangeDetail(null)
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

    }
}