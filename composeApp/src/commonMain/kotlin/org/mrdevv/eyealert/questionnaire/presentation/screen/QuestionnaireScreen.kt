package org.mrdevv.eyealert.questionnaire.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.configCuestionario.data.ConfigCuestionarioImpl
import org.mrdevv.eyealert.configCuestionario.model.dto.CreateCuestionarioConocimientos
import org.mrdevv.eyealert.configCuestionario.model.dto.CreateRespuestaCuestionarioConocimientos
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingButton
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingLoader
import org.mrdevv.eyealert.settings
import org.mrdevv.eyealert.ui.screen.MainScreen

private val settings: Settings = Settings()
//class QuestionnaireScreen: Screen {

@Composable
fun QuestionnaireScreen(navigator: Navigator) {
    var isEnableButton by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val configCuestionarioImpl = ConfigCuestionarioImpl();
    var isLoadingCuestionario by remember { mutableStateOf(false) }
    var respuestas: MutableList<CreateRespuestaCuestionarioConocimientos> =
        mutableListOf()

    var errorMessageCreateCuestionario by remember { mutableStateOf<String?>(null) }

    var respuesta1 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta2 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta3 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta4 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta5 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta6 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta7 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta8 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta9 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta10 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta11 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta12 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta13 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta14 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }
    var respuesta15 by remember { mutableStateOf<CreateRespuestaCuestionarioConocimientos?>(null) }

    if (respuesta1 != null && respuesta2 != null && respuesta3 != null && respuesta4 != null && respuesta5 != null && respuesta6 != null && respuesta7 != null
        && respuesta8 != null && respuesta9 != null && respuesta10 != null && respuesta11 != null && respuesta12 != null && respuesta13 != null && respuesta14 != null
        && respuesta15 != null
    ) {
        isEnableButton = true
    } else {
        isEnableButton = false
    }


    if (isLoadingCuestionario) {
        FloatingLoader()
    }

    Column(
        Modifier.padding(horizontal = 10.dp, vertical = 7.dp).fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Conocimiento sobre el Glaucoma", fontWeight = FontWeight.Bold, fontSize = 17.sp)
        Spacer(Modifier.height(5.dp))
        Text(
            "El presente cuestionario es para evaluar el conocimiento sobre el glaucoma que obtuvo con ayuda de la aplicación móvil con fines académicos, por favor responder cada pregunta con sinceridad.",
            fontSize = 14.sp
        )
        Spacer(Modifier.height(7.dp))
        Text(
            "1. El glaucoma es una enfermedad rara",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta1?.respuesta == "v",
                onClick = {
                    respuesta1 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma es una enfermedad rara",
                        "v",
                        false
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta1?.respuesta == "f",
                onClick = {
                    respuesta1 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma es una enfermedad rara",
                        "f",
                        true
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "2. El glaucoma tiende a ser herado en la familia",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta2?.respuesta == "v",
                onClick = {
                    respuesta2 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma tiende a ser herado en la familia",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta2?.respuesta == "f",
                onClick = {
                    respuesta2 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma tiende a ser herado en la familia",
                        "f",
                        false
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "3. Las personas mayores de 60 años tienen más probabilidades de contraer glaucoma",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta3?.respuesta == "v",
                onClick = {
                    respuesta3 = CreateRespuestaCuestionarioConocimientos(
                        "Las personas mayores de 60 años tienen más probabilidades de contraer glaucoma",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta3?.respuesta == "f",
                onClick = {
                    respuesta3 = CreateRespuestaCuestionarioConocimientos(
                        "Las personas mayores de 60 años tienen más probabilidades de contraer glaucoma",
                        "f",
                        false
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "4. Las personas con gotas para los ojos con esteroides a menudo tienen más probabilidades de contraer glaucoma",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta4?.respuesta == "v",
                onClick = {
                    respuesta4 = CreateRespuestaCuestionarioConocimientos(
                        "Las personas con gotas para los ojos con esteroides a menudo tienen más probabilidades de contraer gluacoma",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta4?.respuesta == "f",
                onClick = {
                    respuesta4 = CreateRespuestaCuestionarioConocimientos(
                        "Las personas con gotas para los ojos con esteroides a menudo tienen más probabilidades de contraer gluacoma",
                        "f",
                        false
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "5. Las personas con miopía alta o hipermetropía tienen más probabilidades de contraer glaucoma",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta5?.respuesta == "v",
                onClick = {
                    respuesta5 = CreateRespuestaCuestionarioConocimientos(
                        "Las personas con miopía alta o hipermetropía tienen más probabilidades de contraer gluacoma",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta5?.respuesta == "f",
                onClick = {
                    respuesta5 = CreateRespuestaCuestionarioConocimientos(
                        "Las personas con miopía alta o hipermetropía tienen más probabilidades de contraer gluacoma",
                        "f",
                        false
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "6. Todos los pacientes con glaucoma han aumentado la presión ocultar",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta6?.respuesta == "v",
                onClick = {
                    respuesta6 = CreateRespuestaCuestionarioConocimientos(
                        "Todos los pacientes con glaucoma han aumentado la presión ocultar",
                        "v",
                        false
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta6?.respuesta == "f",
                onClick = {
                    respuesta6 = CreateRespuestaCuestionarioConocimientos(
                        "Todos los pacientes con glaucoma han aumentado la presión ocultar",
                        "f",
                        true
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "7. El glaucoma puede conducir a la ceguera",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta7?.respuesta == "v",
                onClick = {
                    respuesta7 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma puede conducir a la ceguera",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta7?.respuesta == "f",
                onClick = {
                    respuesta7 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma puede conducir a la ceguera",
                        "f",
                        false
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "8. El campo visual estrecho es un síntoma de glaucoma",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta8?.respuesta == "v",
                onClick = {
                    respuesta8 = CreateRespuestaCuestionarioConocimientos(
                        "El campo visual estrecho es un sintoma de glaucoma",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta8?.respuesta == "f",
                onClick = {
                    respuesta8 = CreateRespuestaCuestionarioConocimientos(
                        "El campo visual estrecho es un sintoma de glaucoma",
                        "f",
                        false
                    )
                }
            )
        }
        Spacer(Modifier.height(7.dp))
        Text(
            "9. El dolor ocular es un síntoma común del glaucoma",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta9?.respuesta == "v",
                onClick = {
                    respuesta9 = CreateRespuestaCuestionarioConocimientos(
                        "El dolor ocular es un síntoma común del glaucoma",
                        "v",
                        false
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta9?.respuesta == "f",
                onClick = {
                    respuesta9 = CreateRespuestaCuestionarioConocimientos(
                        "El dolor ocular es un síntoma común del glaucoma",
                        "f",
                        true
                    )
                }
            )
        }

        Spacer(Modifier.height(7.dp))
        Text(
            "10. Los pacientes con glaucoma mayormente son asintomáticos",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta10?.respuesta == "v",
                onClick = {
                    respuesta10 = CreateRespuestaCuestionarioConocimientos(
                        "Los pacientes con glaucoma mayormente son asintomáticos",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta10?.respuesta == "f",
                onClick = {
                    respuesta10 = CreateRespuestaCuestionarioConocimientos(
                        "Los pacientes con glaucoma mayormente son asintomáticos",
                        "f",
                        false
                    )
                }
            )
        }

        Spacer(Modifier.height(7.dp))
        Text(
            "11. El glaucoma tiene diferentes tipos",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta11?.respuesta == "v",
                onClick = {
                    respuesta11 = CreateRespuestaCuestionarioConocimientos(
                        "El gluacoma tiene diferentes tipos",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta11?.respuesta == "f",
                onClick = {
                    respuesta11 = CreateRespuestaCuestionarioConocimientos(
                        "El gluacoma tiene diferentes tipos",
                        "f",
                        false
                    )
                }
            )
        }

        Spacer(Modifier.height(7.dp))
        Text(
            "12. El deterioro visual del glaucoma se puede restaurar mediante tratamiento",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta12?.respuesta == "v",
                onClick = {
                    respuesta12 = CreateRespuestaCuestionarioConocimientos(
                        "El deterioro visual del glaucoma se puede restaurar mediante tratamiento",
                        "v",
                        false
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta12?.respuesta == "f",
                onClick = {
                    respuesta12 = CreateRespuestaCuestionarioConocimientos(
                        "El deterioro visual del glaucoma se puede restaurar mediante tratamiento",
                        "f",
                        true
                    )
                }
            )
        }

        Spacer(Modifier.height(7.dp))
        Text(
            "13. La visita de seguimiento en la clínica no es necesaria después del tratamiento con láser o la cirugía del glaucoma",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta13?.respuesta == "v",
                onClick = {
                    respuesta13 = CreateRespuestaCuestionarioConocimientos(
                        "La visita de seguimiento en la clínica no es necesaria después del tratamiento con láser o la cirugía del gluacoma",
                        "v",
                        false
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta13?.respuesta == "f",
                onClick = {
                    respuesta13 = CreateRespuestaCuestionarioConocimientos(
                        "La visita de seguimiento en la clínica no es necesaria después del tratamiento con láser o la cirugía del gluacoma",
                        "f",
                        true
                    )
                }
            )
        }

        Spacer(Modifier.height(7.dp))
        Text(
            "14. Se debe tratar la presión intraocular alta",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta14?.respuesta == "v",
                onClick = {
                    respuesta14 = CreateRespuestaCuestionarioConocimientos(
                        "Se debe tratar la presión intraocular alta",
                        "v",
                        true
                    )
                }
            )

            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta14?.respuesta == "f",
                onClick = {
                    respuesta14 = CreateRespuestaCuestionarioConocimientos(
                        "Se debe tratar la presión intraocular alta",
                        "f",
                        false
                    )
                }
            )
        }

        Spacer(Modifier.height(7.dp))
        Text(
            "15. El glaucoma se puede controlar mediante tratamiento",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Verdadero", fontSize = 14.sp)
            RadioButton(
                selected = respuesta15?.respuesta == "v",
                onClick = {
                    respuesta15 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma se puede controlar mediante tratamiento",
                        "v",
                        true
                    )
                }
            )


            Text(text = "Falso", fontSize = 14.sp)
            RadioButton(
                selected = respuesta15?.respuesta == "f",
                onClick = {
                    respuesta15 = CreateRespuestaCuestionarioConocimientos(
                        "El glaucoma se puede controlar mediante tratamiento",
                        "f",
                        false
                    )
                }
            )
        }

        Spacer(Modifier.height(7.dp))

        if (respuesta1 == null || respuesta2 == null || respuesta3 == null || respuesta4 == null || respuesta5 == null || respuesta6 == null || respuesta7 == null
            || respuesta8 == null || respuesta9 == null || respuesta10 == null || respuesta11 == null || respuesta12 == null || respuesta13 == null || respuesta14 == null
            || respuesta15 == null
        ) {
            Text(text = "Responda todas las preguntas para poder enviar el cuestionario.*", fontSize = 12.sp, color = Color.Blue)
        }

        Spacer(Modifier.height(7.dp))


        Button(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF224164),
                contentColor = Color.White
            ),
            enabled = isEnableButton,
            onClick = {
                coroutineScope.launch {
                    isLoadingCuestionario = true
                    respuestas.clear()
                    respuestas.add(respuesta1!!)
                    respuestas.add(respuesta2!!)
                    respuestas.add(respuesta3!!)
                    respuestas.add(respuesta4!!)
                    respuestas.add(respuesta5!!)
                    respuestas.add(respuesta6!!)
                    respuestas.add(respuesta7!!)
                    respuestas.add(respuesta8!!)
                    respuestas.add(respuesta9!!)
                    respuestas.add(respuesta10!!)
                    respuestas.add(respuesta11!!)
                    respuestas.add(respuesta12!!)
                    respuestas.add(respuesta13!!)
                    respuestas.add(respuesta14!!)
                    respuestas.add(respuesta15!!)

                    var cuestionario = CreateCuestionarioConocimientos(
                        settings.getLong("ID", 0),
                        respuestas
                    )

                    println(respuestas)
                    println(cuestionario)

                    configCuestionarioImpl.saveCuestionarioConocimientos(
                        CreateCuestionarioConocimientos(
                            settings.getLong("ID", 0),
                            respuestas
                        )
                    ) { responseCuestionarioConocimientos ->
                        if (responseCuestionarioConocimientos != null) {
                            if (responseCuestionarioConocimientos.code == 201) {
                                settings.set("CUESTIONARIO_COMPLETADO", true)
                                navigator.replaceAll(MainScreen())
                            } else {
                                errorMessageCreateCuestionario =
                                    "Ocurrió un error al momento de guardar el cuestionario."
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(errorMessageCreateCuestionario!!)
                                }
                            }
                        } else {
                            errorMessageCreateCuestionario =
                                "El servidor no se encuentra disponible. Intentelo más tarde :("
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(errorMessageCreateCuestionario!!)
                            }
                        }
                        isLoadingCuestionario = false
                    }
                }



//                    settings.set("CUESTIONARIO_COMPLETADO", true)
//                    navigator.replaceAll(MainScreen())

            }
        ) {
            Text("Enviar")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}