package org.mrdevv.eyealert.evaluation.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Woman
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.evaluation.data.EvaluacionImpl
import org.mrdevv.eyealert.evaluation.data.PreguntasImpl
import org.mrdevv.eyealert.evaluation.model.domain.Pregunta
import org.mrdevv.eyealert.evaluation.model.dto.CrearDetalleEvaluacionDTO
import org.mrdevv.eyealert.evaluation.model.dto.CrearEvaluacionDTO
import org.mrdevv.eyealert.evaluation.model.dto.RequestRespuestasCuestionario
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingButton
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingLoader
import org.mrdevv.eyealert.evaluation.presentation.component.Header
import org.mrdevv.eyealert.ui.components.BoxErrorMessage
import org.mrdevv.eyealert.ui.components.Loader
import kotlin.random.Random

private val settings: Settings = Settings()

public class NewEvaluation : Screen {


    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val keyboardController = LocalSoftwareKeyboardController.current
        val snackbarHostState = remember { SnackbarHostState() }

        val preguntasImpl = PreguntasImpl();
        val evaluacionImpl = EvaluacionImpl()

        val respuestasUsuario = remember { mutableStateMapOf<Long, String>() }

        val navigator = LocalNavigator.currentOrThrow
        var listPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }
        var isLoading by remember { mutableStateOf(true) }
        var errorMessage by remember { mutableStateOf<String?>(null) }


        var errorMessageCreateEvaluation by remember { mutableStateOf<String?>(null) }
        var errorMessageModelAPI by remember { mutableStateOf<String?>(null) }

        var isLoadingEvaluation by remember { mutableStateOf(false) }

        var modalHasVisible by remember { mutableStateOf(false) }
        var textResultEvaluacion by remember { mutableStateOf<String?>(null) }
        var isEnableButton by remember { mutableStateOf(false) }
        val listState = rememberLazyListState()

        val coroutineScope = rememberCoroutineScope()

        val buttonKey = "loadMoreButton"

        val showButton by remember {
            derivedStateOf {
                listState.layoutInfo.visibleItemsInfo.any { it.key == buttonKey }.not()
            }
        }

        var isBottomSheetVisible by remember { mutableStateOf(false) } // Estado del BottomSheet

        LaunchedEffect(isBottomSheetVisible) {
            if (modalHasVisible && !isBottomSheetVisible) {
                navigator.pop()
            }
        }

//        Obtiene las preguntas del backend
        LaunchedEffect(Unit) {
//            delay(3000)
            preguntasImpl.getListPreguntas { response ->
                if (response != null) {
                    if (response.code == 200) {
                        listPreguntas = response.data!!
                    } else if (response?.code == 500) {
                        errorMessage =
                            "Ocurrio un error al momento de cargar las preguntas. Intentelo más tarde :("
                    }
                    isLoading = false
                } else {
                    isLoading = false
                    errorMessage = "El servidor no se encuentra disponible en estos momentos"
                }
            }
        }

        if (isLoadingEvaluation) {
            FloatingLoader()
        }

        // Modal respuesta evaluacion
        if (isBottomSheetVisible) {
            modalHasVisible = true
//            val randomNumber = Random.nextInt(0, 2)
            if (textResultEvaluacion?.contains("bajo") == true) {
                LowRisk(navigator) { isBottomSheetVisible = it }
            } else {
                HighRisk(navigator) { isBottomSheetVisible = it }
            }
        }

        LazyColumn(
            Modifier.fillMaxSize().background(Color(0xFFF6F6F6)),
            state = listState
        ) {

            if (errorMessage == null) {
                stickyHeader {
                    Header(navigator)
                }
            }

            if (isLoading) {
                item {
                    Loader(400)
                }
            }

            if (errorMessage.isNullOrEmpty()) {
                itemsIndexed(listPreguntas) { index, pregunta ->
                    var selectedOption by rememberSaveable { mutableStateOf<String?>(null) } // Estado para la selección

                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                    ) {
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = "${index + 1}. ${pregunta.descripcion}",
                                color = Color.Black
                            )
                            if (pregunta.descripcion.contains("edad")) {
                                Spacer(Modifier.width(5.dp))
                                Text("(mayor o igual de 40 años)", fontWeight = FontWeight.Bold)
                            }
                        }
                        Row(
                            Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            if (pregunta.listRespuestas.isNotEmpty()) {
                                pregunta.listRespuestas.forEach { respuesta ->
                                    Row(
                                        Modifier.clickable {
                                            selectedOption = respuesta.respuesta
                                            respuestasUsuario[pregunta.id] = respuesta.respuesta
                                        },
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        val respClean = respuesta.respuesta.trim()
                                        if (respClean != "Masculino" && respClean != "Femenino") {
                                            Text(text = respuesta.respuesta)
                                        }
                                        if (respClean == "Masculino") {
                                            Icon(
                                                modifier = Modifier.size(40.dp),
                                                imageVector = Icons.Default.Man,
                                                tint = Color(0xFF224164),
                                                contentDescription = "icon man"
                                            )
                                        }
                                        if (respClean == "Femenino") {
                                            Icon(
                                                modifier = Modifier.size(40.dp),
                                                imageVector = Icons.Default.Woman,
                                                tint = Color(0xFFA11AEA),
                                                contentDescription = "icon man"
                                            )
                                        }
                                        RadioButton(
                                            selected = selectedOption == respuesta.respuesta,
                                            onClick = {
                                                keyboardController?.hide()
                                                selectedOption = respuesta.respuesta
                                                respuestasUsuario[pregunta.id] = respuesta.respuesta
                                            }
                                        )
                                    }

                                }
                            } else {
                                Column {
                                    TextField(
                                        value = respuestasUsuario[pregunta.id] ?: "",
                                        onValueChange = { input ->
                                            if (input.all { char -> char.isDigit() }) {
                                                respuestasUsuario[pregunta.id] = input
                                            }
                                        },
                                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                                        colors = TextFieldDefaults.colors(
                                            focusedTextColor = Color(0xFF464646),
                                            focusedContainerColor = Color(0xFFE3E3E3),
                                            unfocusedContainerColor = Color(0xFFE3E3E3),
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        ),
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
                        Column(
                            Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BoxErrorMessage(errorMessage, 100)
                            Spacer(Modifier.height(20.dp))
                            TextButton(
                                modifier = Modifier.wrapContentHeight()
                                    .wrapContentWidth(),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF224164),
                                    contentColor = Color.White
                                ),
                                onClick = {
                                    navigator.pop()
                                }
                            ) {
                                Text("Regresar al Inicio")
                            }
                            Spacer(Modifier.height(20.dp))
                        }
                    }
                }
            }

            if (listPreguntas.size == respuestasUsuario.size && !respuestasUsuario.values.any { it.isEmpty() } && respuestasUsuario.values.any { it.toIntOrNull() ?: 0 >= 40 }) {
                isEnableButton = true
            } else {
                isEnableButton = false
            }

            if (listPreguntas.isNotEmpty()) {
                item(
                    key = buttonKey
                ) {

                    TextButton(
                        modifier = Modifier.wrapContentHeight()
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 30.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF224164),
                            contentColor = Color.White
                        ),
                        enabled = isEnableButton,
                        onClick = {
                            coroutineScope.launch {
                                isLoadingEvaluation = true
                                val edad: Int = respuestasUsuario[1]!!.toInt()
                                val sexo = if (respuestasUsuario[2]!! == "Masculino") {
                                    1
                                } else {
                                    0
                                }
                                val PIOEvelada = if (respuestasUsuario[3]!! == "Sí") {
                                    1
                                } else {
                                    0
                                }
                                val historialFamiliar = if (respuestasUsuario[4]!! == "Sí") {
                                    1
                                } else {
                                    0
                                }
                                val diabetes = if (respuestasUsuario[5]!! == "Sí") {
                                    1
                                } else {
                                    0
                                }
                                val hipertension = if (respuestasUsuario[6]!! == "Sí") {
                                    1
                                } else {
                                    0
                                }
                                val catarata = if (respuestasUsuario[7]!! == "Sí") {
                                    1
                                } else {
                                    0
                                }


                                val respuestasCuestionario = RequestRespuestasCuestionario(
                                    edad,
                                    sexo,
                                    PIOEvelada,
                                    historialFamiliar,
                                    diabetes,
                                    hipertension,
                                    catarata
                                )
                                evaluacionImpl.getNivelRiesgoEvaluacion(respuestasCuestionario) { response ->
                                    if (response != null) {
                                        if (response.status == 200) {
                                            val tiempoPrediccionInicio = response.tiempoPredicionInicio
                                            val tiempoPrediccionFin = response.tiempoPredicionFin
                                            val tiempoPrediccion = response.predictionTimeMs
                                            val resultado = response.resultEvaluation.toInt()
                                            val usuarioId = settings.getLong("ID", 0)

                                            var detalleEvaluacion: MutableList<CrearDetalleEvaluacionDTO> =
                                                mutableListOf()


                                            var idRespuestaSi: Long = 0
                                            var idRespuestaNo: Long = 0
                                            var idRespuestaMasculino: Long = 0
                                            var idRespuestaFemenino: Long = 0

                                            listPreguntas.forEach { pregunta ->
                                                pregunta.listRespuestas.forEach { pregunta ->
                                                    if (pregunta.respuesta == "Sí") {
                                                        idRespuestaSi = pregunta.id
                                                    }

                                                    if (pregunta.respuesta == "No") {
                                                        idRespuestaNo = pregunta.id
                                                    }

                                                    if (pregunta.respuesta == "Masculino") {
                                                        idRespuestaMasculino = pregunta.id
                                                    }

                                                    if (pregunta.respuesta == "Femenino") {
                                                        idRespuestaFemenino = pregunta.id
                                                    }
                                                }
                                            }

                                            respuestasUsuario.forEach { respuesta ->
                                                var respuestaId: Long? = null

                                                var respuestaTexto: String = ""

                                                if (respuesta.value == "Sí") {
                                                    respuestaId = idRespuestaSi
                                                }

                                                if (respuesta.value == "No") {
                                                    respuestaId = idRespuestaNo
                                                }

                                                if (respuesta.value == "Masculino") {
                                                    respuestaId = idRespuestaMasculino
                                                }

                                                if (respuesta.value == "Femenino") {
                                                    respuestaId = idRespuestaFemenino
                                                }

                                                if (respuesta.value != "Femenino" && respuesta.value != "Masculino" && respuesta.value != "Sí" && respuesta.value != "No") {
                                                    respuestaTexto = respuesta.value
                                                }

                                                var crearDetalleEvaluacionDTO =
                                                    CrearDetalleEvaluacionDTO(
                                                        respuesta.key,
                                                        respuestaId,
                                                        respuestaTexto
                                                    )
                                                detalleEvaluacion.add(crearDetalleEvaluacionDTO)
                                            }

                                            evaluacionImpl.crearEvaluacion(
                                                CrearEvaluacionDTO(
                                                    tiempoPrediccionInicio,
                                                    tiempoPrediccionFin,
                                                    tiempoPrediccion,
                                                    resultado,
                                                    usuarioId,
                                                    detalleEvaluacion
                                                )
                                            ) { responseCreateEvaluation ->
                                                if (responseCreateEvaluation != null) {
                                                    if (responseCreateEvaluation.code == 201) {
                                                        textResultEvaluacion = response.message
                                                        isBottomSheetVisible = true
                                                    } else {
                                                        errorMessageCreateEvaluation =
                                                            "Ocurrió un error al momento de guardar la evaluación."
                                                    }
                                                } else {
                                                    errorMessageCreateEvaluation =
                                                        "El servidor no se encuentra disponible. Intentelo más tarde :("
                                                }
                                            }
                                        } else {
                                            errorMessageModelAPI =
                                                "Ocurrió un error al momento de realizar la evaluación."
                                        }
                                    } else {
                                        coroutineScope.launch {
                                            errorMessageModelAPI = "El servidor para realizar la evaluación no se encuentra disponible. Intentelo más tarde :("
                                            snackbarHostState.showSnackbar(errorMessageModelAPI!!)
                                        }
                                    }
                                    isLoadingEvaluation = false
                                }

                            }
                        }
                    ) {
                        Text("Realizar evaluación")
                    }
                }

            }
        }

        if (showButton && listPreguntas.isNotEmpty()) {
            FloatingButton(listPreguntas, listState, coroutineScope)
        }
        SnackbarHost(hostState = snackbarHostState)
    }

}