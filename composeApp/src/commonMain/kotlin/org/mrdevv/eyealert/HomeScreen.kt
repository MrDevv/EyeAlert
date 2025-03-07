package org.mrdevv.eyealert

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russhwolf.settings.Settings


private val settings: Settings = Settings()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(
        Modifier.fillMaxSize().padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            Text("Bienvenido", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(5.dp))
            Text(settings.getString("NAME", ""), fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(Modifier.height(5.dp))
        Text(
            "Evalúa tu salud ocular y mejora tu conocimiento sobre el glaucoma",
            color = Color.White
        )
        Spacer(Modifier.height(15.dp))
        //            CONTENEDOR ULTIMAS EVALUACIONES
        Column(
            Modifier.clip(RoundedCornerShape(6.dp)).fillMaxWidth().background(Color.White)
        ) {
            Row(
                Modifier.fillMaxWidth().background(Color(0xFF002249)),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("MIS ULTIMAS EVALUACIONES", color = Color.White, fontWeight = FontWeight.Bold)
            }
            //            ULTIMAS EVALUACIONES
            Column(Modifier.fillMaxWidth().padding(10.dp)) {
//                CARD EVALUACION
                Row(
                    Modifier.clip(RoundedCornerShape(3.dp)).fillMaxWidth()
                        .background(Color(0xFF0C6D40)).padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.Mood,
                            contentDescription = "icon evaluation",
                            tint = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                        Text(
                            "RIESGO BAJO",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("5/12/2024", color = Color.White)
                        Spacer(Modifier.height(4.dp))
                        Row {
                            Text("Tiempo:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.width(5.dp))
                            Text("2 segundos", fontSize = 12.sp, color = Color.White)
                        }
                        Spacer(Modifier.height(4.dp))
                        Icon(Icons.Filled.PostAdd, contentDescription = null, tint = Color.White)
                    }
                }

                Spacer(Modifier.height(5.dp))

                Row(
                    Modifier.clip(RoundedCornerShape(3.dp)).fillMaxWidth()
                        .background(Color(0xFFCC3724)).padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.SentimentDissatisfied,
                            contentDescription = "icon evaluation",
                            tint = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                        Text(
                            "RIESGO ALTO",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("5/12/2024", color = Color.White)
                        Spacer(Modifier.height(4.dp))
                        Row {
                            Text("Tiempo:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.width(5.dp))
                            Text("2 segundos", fontSize = 12.sp, color = Color.White)
                        }
                        Spacer(Modifier.height(4.dp))
                        Icon(Icons.Filled.PostAdd, contentDescription = null, tint = Color.White)
                    }
                }

                Spacer(Modifier.height(5.dp))

                Row(
                    Modifier.clip(RoundedCornerShape(3.dp)).fillMaxWidth()
                        .background(Color(0xFFCC3724)).padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.SentimentDissatisfied,
                            contentDescription = "icon evaluation",
                            tint = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                        Text(
                            "RIESGO ALTO",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("5/12/2024", color = Color.White)
                        Spacer(Modifier.height(4.dp))
                        Row {
                            Text("Tiempo:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.width(5.dp))
                            Text("2 segundos", fontSize = 12.sp, color = Color.White)
                        }
                        Spacer(Modifier.height(4.dp))
                        Icon(Icons.Filled.PostAdd, contentDescription = null, tint = Color.White)
                    }
                }

                Spacer(Modifier.height(5.dp))

                Button(
                    modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF224164),
                        contentColor = Color.White
                    ),
                    onClick = {}
                ) {
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
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

            val items = listOf(
                "Las personas mayores de 60 años corren mayor riesgo de tener glaucoma...",
                "La pérdida de visión por causa del glaucoma es permanente...",
                "El historial familiar es un factor importante del glaucoma..."
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items.size) { index ->
                    Card(
                        modifier = Modifier
                            .width(250.dp)
                            .height(110.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF6DB2FF)
                        ),
                        shape = RoundedCornerShape(8.dp)
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
                                        text = items[index],
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

        Spacer(Modifier.height(10.dp))

    }
}