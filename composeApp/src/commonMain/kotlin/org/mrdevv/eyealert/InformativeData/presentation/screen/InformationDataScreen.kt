package org.mrdevv.eyealert.InformativeData.presentation.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.russhwolf.settings.Settings
import org.mrdevv.eyealert.InformativeData.data.DatoInformativoImpl
import org.mrdevv.eyealert.InformativeData.model.domain.DatoInformativo
import org.mrdevv.eyealert.InformativeData.presentation.component.ButtonMoreInformation
import org.mrdevv.eyealert.InformativeData.presentation.component.ButtonVideo
import org.mrdevv.eyealert.ui.components.BoxErrorMessage
import org.mrdevv.eyealert.ui.components.HeaderScreens
import org.mrdevv.eyealert.ui.components.Loader

private val settings: Settings = Settings()

class InformationDataScreen() : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val datoInformativoImpl = DatoInformativoImpl()
        var listInformativeData by remember { mutableStateOf<List<DatoInformativo>>(emptyList()) }

        var errorMessage by remember { mutableStateOf<String?>(null) }

        var isLoading by remember { mutableStateOf(true) }

        var selectedInformativeData by remember { mutableStateOf<DatoInformativo?>(null) }

        LaunchedEffect(Unit) {
            datoInformativoImpl.getAllDatosInformativos { response ->
                if (response != null) {
                    if (response.code == 200) {
                        if (response.data != null) {
                            listInformativeData = response.data
                        }
                    } else if (response.code == 500) {
                        errorMessage =
                            "Ocurrió un error al momento de cargar los datos informativos. Intentalo más tarde :("
                    }
                } else {
                    errorMessage = "El servidor no se encuentra disponible en estos momentos"
                }
                isLoading = false
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
                        .background(Color(0xFF6DB2FF))
                        .padding(bottom = 10.dp, start = 15.dp)
                ) {
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
                                modifier = Modifier.size(120.dp),
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = "icon led informative data",
                                tint = Color(0xFFC1CA3D)
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                if (data.fuenteMultimedia.isNotEmpty()) {
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



        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
                HeaderScreens(settings)
            }

            Spacer(Modifier.height(15.dp))

//            CONTENEDOR DATOS INFORMATIVOS
            Column(
                Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                    .background(Color.White)
            ) {
//                CABECERA
                Row(
                    Modifier.fillMaxWidth()
                        .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Datos informativos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }

                if (isLoading) {
                    Loader(100)
                }

                if (errorMessage.isNullOrEmpty() && !isLoading) {
                    LazyColumn(
                        Modifier.fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        listInformativeData.chunked(2).forEach { rowItems ->

                            item {
                                Row(
                                    Modifier.fillParentMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    rowItems.forEach { item ->
//                                      CARDS DATOS INFORMATIVOS
                                        Card(
                                            modifier = Modifier
                                                .width(170.dp)
                                                .height(110.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = Color(0xFF6DB2FF)
                                            ),
                                            shape = RoundedCornerShape(8.dp),
                                            onClick = {
                                                selectedInformativeData = item
                                            }
                                        ) {
                                            Box(
                                                modifier = Modifier.padding(
                                                    horizontal = 16.dp,
                                                    vertical = 8.dp
                                                ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Column(horizontalAlignment = Alignment.Start) {
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth()
                                                            .weight(2.6f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = limitText(
                                                                item.descripcion,
                                                                6
                                                            ),
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

                                Spacer(Modifier.height(8.dp))
                            }

                        }
                    }
                }

                if (errorMessage != null && !isLoading) {
                    Spacer(Modifier.height(50.dp))
                    BoxErrorMessage(errorMessage, 100)
                }
            }
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