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
import org.mrdevv.eyealert.InformativeData.presentation.component.CardInformativeData
import org.mrdevv.eyealert.InformativeData.presentation.component.ModalInformationData
import org.mrdevv.eyealert.evaluation.presentation.screen.NewEvaluation
import org.mrdevv.eyealert.evaluation.data.EvaluacionImpl
import org.mrdevv.eyealert.evaluation.model.domain.Evaluacion
import org.mrdevv.eyealert.evaluation.model.dto.ResponseDataEvaluacion
import org.mrdevv.eyealert.evaluation.presentation.component.CardEvaluation
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingLoader
import org.mrdevv.eyealert.evaluation.presentation.component.ModalDetailEvaluation
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

            ModalDetailEvaluation(fecha, textResult, colorResult, hora, data) {
                detailEvaluacion = it
            }
        }

        LaunchedEffect(Unit) {
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
                if (response != null) {
                    if (response.code == 200) {
                        if (response.data != null) {
                            listThreeInformativeData = response.data
                        }
                    } else if (response.code == 500) {
                        errorMessageInformativeData =
                            "Ocurrio un error al momento de cargar los datos informativos. Intentelo más tarde :("
                    }
                } else {
                    errorMessageInformativeData =
                        "El servidor no se encuentra disponible en estos momentos"
                }
                isLoadingInformativeData = false;
            }
        }

        selectedInformativeData?.let { data ->
            ModalInformationData(data){
                selectedInformativeData = it
            }
        }

        LazyColumn(
            Modifier.fillMaxSize().padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            item {
                HeaderScreens(settings)
                Spacer(Modifier.height(15.dp))
            }

            //            CONTENEDOR ULTIMAS EVALUACIONES
            item {
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
                            Column {
                                if (listLastEvaluations.isEmpty()) {
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
                                } else {
                                    listLastEvaluations.forEach { evaluacion ->
                                        var colorBox: Int
                                        var textBox: String
                                        var iconBox: ImageVector = Icons.Default.QuestionMark

                                        var fechaFormat = evaluacion.fecha.split(" ")[0]

                                        if (evaluacion.resultado == "bajo") {
                                            colorBox = 0xFF2EA26C.toInt()
                                            textBox = "RIESGO BAJO"
                                            iconBox = Icons.Filled.Mood
                                        } else {
                                            colorBox = 0xFFD44D3C.toInt()
                                            textBox = "RIESGO ALTO"
                                            iconBox = Icons.Filled.SentimentDissatisfied
                                        }

                                        CardEvaluation(
                                            evaluacion,
                                            colorBox,
                                            iconBox,
                                            textBox,
                                            fechaFormat
                                        ) {
                                            selectedEvaluacion = it
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
            }


            //        CONTENEDOR DATOS INFORMATIVOS
            item {
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
                                CardInformativeData(infomativeData){
                                    selectedInformativeData = it
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
    }
}