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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.evaluation.data.PreguntasImpl
import org.mrdevv.eyealert.evaluation.model.domain.Pregunta
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingButton
import org.mrdevv.eyealert.evaluation.presentation.component.Header
import org.mrdevv.eyealert.ui.components.BoxErrorMessage
import org.mrdevv.eyealert.ui.components.Loader
import kotlin.random.Random

public class NewEvaluation : Screen {


    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val preguntasImpl = PreguntasImpl();
        val navigator = LocalNavigator.currentOrThrow
        var listPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }
        var isLoading by remember { mutableStateOf(true) }
        var errorMessage by remember { mutableStateOf<String?>(null) }


        var modalHasVisible by remember { mutableStateOf(false) }

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
            if (modalHasVisible && !isBottomSheetVisible){
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
                println("preguntas ${listPreguntas}")
            }
        }

        // Modal respuesta evaluacion
        if (isBottomSheetVisible) {
            modalHasVisible = true
            val randomNumber = Random.nextInt(0, 2)
            if (randomNumber == 0){
                LowRisk(navigator) { isBottomSheetVisible = it }
            }else{
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
                                        val respClean = respuesta.respuesta.trim()
                                        if (respClean != "Masculino" && respClean != "Femenino"){
                                            Text(text = respuesta.respuesta)
                                        }
                                        if (respClean == "Masculino"){
                                            Icon(
                                                modifier = Modifier.size(40.dp),
                                                imageVector =  Icons.Default.Man,
                                                tint = Color(0xFF224164),
                                                contentDescription = "icon man")
                                        }
                                        if (respClean == "Femenino"){
                                            Icon(
                                                modifier = Modifier.size(40.dp),
                                                imageVector =  Icons.Default.Woman,
                                                tint = Color(0xFFA11AEA),
                                                contentDescription = "icon man")
                                        }
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
                        Column(Modifier.fillParentMaxSize(), verticalArrangement =  Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
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
                            isBottomSheetVisible = true // Al hacer clic, se muestra el BottomSheet

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

    }

}