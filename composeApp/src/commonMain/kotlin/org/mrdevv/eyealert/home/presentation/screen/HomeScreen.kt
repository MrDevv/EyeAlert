package org.mrdevv.eyealert.home.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings
import org.mrdevv.eyealert.InformativeData.data.DatoInformativoImpl
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo
import org.mrdevv.eyealert.InformativeData.presentation.component.ButtonMoreInformation
import org.mrdevv.eyealert.InformativeData.presentation.component.ButtonVideo
import org.mrdevv.eyealert.evaluation.presentation.screen.NewEvaluation
import org.mrdevv.eyealert.evaluation.data.EvaluacionImpl
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion
import org.mrdevv.eyealert.evaluation.model.dto.ResponseDataEvaluacion
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingLoader
import org.mrdevv.eyealert.ui.components.BoxErrorMessage
import org.mrdevv.eyealert.ui.components.HeaderScreens
import org.mrdevv.eyealert.ui.components.Loader


private val settings: Settings = Settings()


public class HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val evaluacionImpl = EvaluacionImpl()
        val datoInformativoImpl = DatoInformativoImpl()

        var listLastEvaluations by remember { mutableStateOf<List<Evaluacion>>(emptyList()) }
        var listThreeInformativeData by remember { mutableStateOf<List<DatoInformativo>>(emptyList()) }

        var errorMessage by remember { mutableStateOf<String?>(null) }
        var errorMessageInformativeData by remember { mutableStateOf<String?>(null) }

        var isLoading by remember { mutableStateOf(true) }
        var isLoadingInformativeData by remember { mutableStateOf(true) }

        var selectedInformativeData by remember { mutableStateOf<DatoInformativo?>(null) }

        var selectedEvaluacion by remember { mutableStateOf<Long?>(null) }
        var isLoadingDetail by remember { mutableStateOf(false) }
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


        LaunchedEffect(Unit) {
//                delay(4000)
            evaluacionImpl.getLastEvaluacionesByUser(settings.getLong("ID", 0)) { response ->
                if (response != null) {
                    if (response.code == 200) {
                        if (response.data != null) {
                            listLastEvaluations = response.data.evaluaciones
                        }
                    } else if (response.code == 500) {
                        errorMessage =
                            "Ocurrio un error al momento de cargar las ultimas evaluaciones. Intentelo más tarde :("
                    }
                } else {
                    errorMessage = "El servidor no se encuentra disponible en estos momentos"
                }
                isLoading = false;
            }

            datoInformativoImpl.getTresDatosInformativosAleatorios { response ->
                println(response)
                if (response != null){
                    if (response.code == 200) {
                        if (response.data != null) {
                            listThreeInformativeData = response.data
                        }
                    } else if (response.code == 500) {
                        errorMessageInformativeData = "Ocurrio un error al momento de cargar los datos informativos. Intentelo más tarde :("
                    }
                }else {
                    errorMessageInformativeData = "El servidor no se encuentra disponible en estos momentos"
                }
                isLoadingInformativeData = false;
            }
        }



        selectedInformativeData?.let { data ->
            ModalBottomSheet(
                onDismissRequest = { selectedInformativeData = null },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                contentWindowInsets = { WindowInsets(0, 0, 0, 0) },
                dragHandle = {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .background(Color(0xFF6DB2FF)),
                        contentAlignment = Alignment.Center
                    ){
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
                        .background(Color(0xFF6DB2FF))
                        .padding(bottom = 10.dp, start = 15.dp)
                ){
                    Text(
                        "¿Sabías qué...?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        Modifier.padding(20.dp)
                    ) {
                        Text(text = data.descripcion)

                        Spacer(modifier = Modifier.height(50.dp))

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Icon(
                                modifier =  Modifier.size(120.dp),
                                imageVector =  Icons.Default.Lightbulb,
                                contentDescription = "icon led informative data",
                                tint = Color(0xFFC1CA3D))
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                if (data.fuenteMultimedia.isNotEmpty()){
                                    ButtonVideo(data.fuenteMultimedia)
                                }
                            }
                            ButtonMoreInformation(data.fuente)
                        }

                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }

        Column(
            Modifier.fillMaxSize().padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            HeaderScreens(settings)
            Spacer(Modifier.height(15.dp))
            //            CONTENEDOR ULTIMAS EVALUACIONES
            Column(
                Modifier.clip(RoundedCornerShape(6.dp)).fillMaxWidth().background(Color.White)
            ) {
                Row(
                    Modifier.fillMaxWidth().background(Color(0xFF002249)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "MIS ULTIMAS EVALUACIONES",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                //            ULTIMAS EVALUACIONES
                Column(Modifier.fillMaxWidth().padding(10.dp)) {

                    if (isLoading) {
                        Loader(60)
                    }

//                CARD EVALUACION
                    if (errorMessage.isNullOrEmpty() && !isLoading) {
                        LazyColumn {
                            if (listLastEvaluations.isEmpty()) {
                                item {
                                    Box(
                                        Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 10.dp)
                                    ) {
                                        Text(
                                            "Aún no tienes evaluaciones, realiza una presionando en el botón de abajo.",
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Light,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            } else {
                                items(listLastEvaluations) { evaluacion ->
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
                        BoxErrorMessage(errorMessage, 50)
                    }

                    Button(
                        modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF224164),
                            contentColor = Color.White
                        ),
                        onClick = {
                            navigator.push(NewEvaluation())
                        }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.AddBox, contentDescription = null)
                            Spacer(Modifier.width(5.dp))
                            Text("Nueva evaluación")
                        }
                    }
                }
            }

            Spacer(Modifier.height(15.dp))

            //        CONTENEDOR DATOS INFORMATIVOS
            Column(
                Modifier.clip(RoundedCornerShape(6.dp)).fillMaxWidth().background(Color.White)
            ) {
                Row(
                    Modifier.fillMaxWidth().background(Color(0xFF002249)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("SABIAS QUE...", color = Color.White, fontWeight = FontWeight.Bold)
                }

                if (isLoadingInformativeData) {
                    Loader(60)
                }

                if (errorMessageInformativeData.isNullOrEmpty() && !isLoadingInformativeData) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(listThreeInformativeData) { infomativeData ->
                            Card(
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(110.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF6DB2FF)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                onClick = {
                                    selectedInformativeData = infomativeData
                                }
                            ) {
                                Box(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.Start) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth().weight(2.6f),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = limitText(infomativeData.descripcion, 12),
                                                fontSize = 14.sp,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        Icon(
                                            imageVector = Icons.Default.Lightbulb,
                                            contentDescription = null,
                                            tint = Color(0xFFB2FF59),
                                            modifier = Modifier.size(32.dp).weight(1f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                if (errorMessageInformativeData != null && !isLoadingInformativeData) {
                    BoxErrorMessage(errorMessageInformativeData, 50)
                }
            }

            Spacer(Modifier.height(10.dp))

        }
    }

    fun limitText(text: String, limit: Int): String {
        val words = text.split(" ")
        return if (words.size > limit) {
            words.take(limit).joinToString(" ") + "..."
        } else {
            text
        }
    }
}

