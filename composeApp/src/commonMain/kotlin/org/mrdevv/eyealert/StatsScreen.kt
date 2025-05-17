package org.mrdevv.eyealert

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.russhwolf.settings.Settings
import org.mrdevv.eyealert.stats.data.StatsImpl
import org.mrdevv.eyealert.stats.domain.dto.IndiceConocimiento
import org.mrdevv.eyealert.stats.domain.dto.TasaAcierto
import org.mrdevv.eyealert.stats.domain.dto.TiempoPromedio
import org.mrdevv.eyealert.stats.domain.usecase.IStats
import org.mrdevv.eyealert.ui.components.HeaderScreens



class StatsScreen : Screen{

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val statsImpl = StatsImpl()
        var dataTasa by remember { mutableStateOf<TasaAcierto?>(null) }
        var dataTiempo by remember { mutableStateOf<TiempoPromedio?>(null) }
        var dataConocimiento by remember { mutableStateOf<IndiceConocimiento?>(null) }


        LaunchedEffect(Unit){
            statsImpl.obtenerTasaAcierto { response ->
                println(response)
                if (response != null) {
                    if (response.code == 200) {
                        dataTasa = response.data
                    }
                }
            }

            statsImpl.obtenerTiempoPromedio { response ->
                println(response)
                if (response != null) {
                    if (response.code == 200) {
                        dataTiempo = response.data
                    }
                }
            }

            statsImpl.obtenerIndiceConocimiento { response ->
                println(response)
                if (response != null) {
                    if (response.code == 200) {
                        dataConocimiento = response.data
                    }
                }
            }
        }


        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
                HeaderScreens(settings)
            }

            Spacer(Modifier.height(15.dp))

            Column(Modifier.padding(10.dp)) {

                Column(
                    Modifier.clip(RoundedCornerShape(10.dp)).fillMaxWidth().background(Color.White)
                ) {
                    Row(
                        Modifier.fillMaxWidth().background(Color(0xFF5A9EEA)).padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "TASA DE ACIERTOS",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Row(
                        Modifier.fillMaxWidth().background(Color.White).padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column {
                            Text("Total de evaluaciones: ${dataTasa?.totalEvaluaciones}")
                            Spacer(Modifier.height(3.dp))
                            Text("Evaluaciones acertadas: ${dataTasa?.evaluacionesAcertadas}")
                            Spacer(Modifier.height(3.dp))
                            Text("Evaluaciones no acertadas: ${dataTasa?.evaluacionesNoAcertadas}")
                        }

                        Column {
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(text = "${dataTasa?.tasaAcierto}", fontSize = 40.sp, fontWeight = FontWeight.Bold ,color = Color(0xFF0C4D96))
                                Text(text = "%", fontSize = 35.sp, fontWeight = FontWeight.Bold ,color = Color(0xFF0C4D96))
                            }

                        }
                    }
                }

                Spacer(Modifier.height(30.dp))

                Column(
                    Modifier.clip(RoundedCornerShape(10.dp)).fillMaxWidth().background(Color.White)
                ) {
                    Row(
                        Modifier.fillMaxWidth().background(Color(0xFF5A9EEA)).padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "TIEMPO PROMEDIO DE PREDICCIÓN",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Row(
                        Modifier.fillMaxWidth().background(Color.White).padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column {
                            Text("Total de evaluaciones: ${dataTiempo?.totalEvaluaciones}")
                        }

                        Column {
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(text = "${dataTiempo?.tiempoPromedioPrediccion?.toInt()}", fontSize = 40.sp, fontWeight = FontWeight.Bold ,color = Color(0xFF0C4D96))
                                Text(text = "ms", fontSize = 35.sp, fontWeight = FontWeight.Bold ,color = Color(0xFF0C4D96))
                            }
                        }
                    }
                }

                Spacer(Modifier.height(30.dp))

                Column(
                    Modifier.clip(RoundedCornerShape(10.dp)).fillMaxWidth().background(Color.White)
                ) {
                    Row(
                        Modifier.fillMaxWidth().background(Color(0xFF5A9EEA)).padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "ÍNDICE DE CONOCIMIENTO",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Row(
                        Modifier.fillMaxWidth().background(Color.White).padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(Modifier.weight(3f)) {
                            Text("Puntaje total obtenido: ${dataConocimiento?.puntajeTotalObtenido}")
                            Spacer(Modifier.height(3.dp))
                            Text("Número de participantes: ${dataConocimiento?.numeroParticipantes}")
                            Spacer(Modifier.height(3.dp))
                            Text("Puntaje max. por cuestionario: ${dataConocimiento?.puntajeTotalPosible}")
                        }
                        Column(Modifier.weight(1.1f)) {
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(text = "${dataConocimiento?.IndiceConocimiento}", fontSize = 30.sp, fontWeight = FontWeight.Bold ,color = Color(0xFF0C4D96))
                                Text(text = "%", fontSize = 25.sp, fontWeight = FontWeight.Bold ,color = Color(0xFF0C4D96))
                            }
                        }
                    }
                }


            }


        }
    }

}
