package org.mrdevv.eyealert.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.russhwolf.settings.Settings
import eyealert.composeapp.generated.resources.Res
import eyealert.composeapp.generated.resources.logo
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.mrdevv.eyealert.configCuestionario.data.ConfigCuestionarioImpl
import org.mrdevv.eyealert.evaluation.presentation.component.FloatingLoader
import org.mrdevv.eyealert.questionnaire.presentation.screen.QuestionnaireScreen
import org.mrdevv.eyealert.settings
import org.mrdevv.eyealert.ui.navigatorBarTabs.HomeTab
import org.mrdevv.eyealert.ui.navigatorBarTabs.InformationDataTab
import org.mrdevv.eyealert.ui.navigatorBarTabs.MyEvaluationsTab
import org.mrdevv.eyealert.ui.navigatorBarTabs.StatsTab

private val settings: Settings = Settings()

class MainScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val configCuestionarioImpl = ConfigCuestionarioImpl();
        var diasEspera by remember { mutableStateOf<Int?>(null) }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var isLoadingDiasEspera by remember { mutableStateOf(false) }

        if (isLoadingDiasEspera) {
            FloatingLoader()
        }

        LaunchedEffect(Unit) {
            isLoadingDiasEspera = true
            configCuestionarioImpl.getDiasEspera { response ->
                println("response de la api: $response")
                if (response != null) {
                    if (response.code == 200) {
                        if (response.data != null) {
                            diasEspera = response.data.diasEspera
                            isLoadingDiasEspera = false
                        }
                    }
                } else {
                    errorMessage = "El servidor no se encuentra disponible en estos momentos"
                }
            }
        }

        println("dias espera: $diasEspera")


        if (diasEspera != null){



        val currentMoment = Clock.System.now()
        val currentDate = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault()).date
        val fechaCreacionUsuario = settings.getString("FECHA_CREACION", "")

        val partes = fechaCreacionUsuario.split(" ") // ["12/05/2025", "19:32"]
        val fecha = partes[0].split("/") // ["12", "05", "2025"]

        val dia = fecha[0].toInt()
        val mes = fecha[1].toInt()
        val year = fecha[2].toInt()

        val fechaCreacionUsuarioFormateada = LocalDate(year = year, monthNumber = mes, dayOfMonth = dia)


        println("fecha create: ${settings.getString("FECHA_CREACION", "")}")
        println("fecha creacion formateada: $fechaCreacionUsuarioFormateada")
        val fechaCuestionario = fechaCreacionUsuarioFormateada.plus(DatePeriod(days = diasEspera!!))
//        val fechaCuestionario = LocalDate(2025, 5, 30)
        println("fecha para mostrar cuestionario: $fechaCuestionario")
        println("mostrar cuestionario: ${currentDate >= fechaCuestionario}" )
//            return

        println("usuario completo el cuestionario: ${settings.getBoolean("CUESTIONARIO_COMPLETADO", false)}")

//        if (currentDate == fechaCuestionario && !settings.getBoolean("CUESTIONARIO_COMPLETADO", false)){
        if (currentDate >= fechaCuestionario && !settings.getBoolean("CUESTIONARIO_COMPLETADO", false)){
            Scaffold{ innerPadding ->
                Column(
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    QuestionnaireScreen(navigator)
                }
            }
        }else{


        var expanded by remember { mutableStateOf(false) }  // Estado para mostrar u ocultar el menú
        val navigator = LocalNavigator.currentOrThrow


        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    it,
                    listOf(HomeTab, MyEvaluationsTab, InformationDataTab, StatsTab)
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                ambientColor = Color.Black.copy(alpha = 0.8f),
                                spotColor = Color.Black.copy(alpha = 0.9f)
                            ),
                        navigationIcon = {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        modifier = Modifier.size(24.dp),
                                        painter = painterResource(Res.drawable.logo),
                                        contentDescription = "logo app"
                                    )
                                    Text("EyeAlert", color = Color.White, fontSize = 13.sp)
                                }

                                IconButton(onClick = {
                                    expanded = true
                                }) {  // Abre el menú al hacer clic
                                    Icon(
                                        Icons.Filled.Menu,
                                        contentDescription = "menu icon",
                                        Modifier.size(30.dp),
                                        tint = Color.White
                                    )
                                }
                            }
                        },
                        title = {},
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF1976DF)
                        ),
                        actions = {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                offset = DpOffset(x = 0.dp, y = 20.dp)
                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                Icons.Default.SupervisedUserCircle,
                                                contentDescription = ""
                                            )
                                            Spacer(Modifier.width(5.dp))
                                            Text("Editar Perfil")
                                        }
                                    },
                                    onClick = {
                                        println("editar perfil")
                                        expanded = false
                                    }
                                )

                                DropdownMenuItem(
                                    text = {
                                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                Icons.Default.PowerSettingsNew,
                                                contentDescription = "",
                                                tint = Color.Red
                                            )
                                            Spacer(Modifier.width(5.dp))
                                            Text("Cerrar sesión", color = Color.Red)
                                        }
                                    },
                                    onClick = {
                                        navigator.replaceAll(AuthScreen())
                                        settings.clear()
                                        println("cerrando sesión")
                                        expanded = false
                                    }
                                )
                            }

                        }
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = Color.White,
                        contentColor = Color(0xFF1976DF),
                        modifier = Modifier.shadow(
                            elevation = 16.dp,
                            ambientColor = Color.Black,
                            spotColor = Color.Black
                        )
                    ) {
                        val tabNavigator: TabNavigator = LocalTabNavigator.current


                        NavigationBarItem(
                            selected = tabNavigator.current.key == HomeTab.key,

                            label = {
                                Text(
                                    text = HomeTab.options.title,
                                    fontSize = 8.sp
                                )
                            },
                            icon = {
                                Icon(
                                    painter = HomeTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = { tabNavigator.current = HomeTab },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color(0xFF1976DF),
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color(0xFF1976DF),
                                indicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.clip(
                                RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                )
                            ).background(
                                brush = Brush.verticalGradient(
                                    colors = if (tabNavigator.current.key == HomeTab.key) listOf(
                                        Color(0xFF1976DF),
                                        Color(0xFF0C4D96)
                                    ) else listOf(Color.Transparent, Color.Transparent)
                                )
                            )
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == MyEvaluationsTab.key,
                            label = { Text(MyEvaluationsTab.options.title, fontSize = 8.sp) },
                            icon = {
                                Icon(
                                    modifier = Modifier,
                                    painter = MyEvaluationsTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = { tabNavigator.current = MyEvaluationsTab },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color(0xFF1976DF),
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color(0xFF1976DF),
                                indicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.clip(
                                RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                )
                            ).background(
                                brush = Brush.verticalGradient(
                                    colors = if (tabNavigator.current.key == MyEvaluationsTab.key) listOf(
                                        Color(0xFF1976DF),
                                        Color(0xFF0C4D96)
                                    ) else listOf(Color.Transparent, Color.Transparent)
                                )
                            )
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == InformationDataTab.key,
                            label = { Text(InformationDataTab.options.title, fontSize = 7.sp) },
                            icon = {
                                Icon(
                                    painter = InformationDataTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = { tabNavigator.current = InformationDataTab },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color(0xFF1976DF),
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color(0xFF1976DF),
                                indicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.clip(
                                RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                )
                            ).background(
                                brush = Brush.verticalGradient(
                                    colors = if (tabNavigator.current.key == InformationDataTab.key) listOf(
                                        Color(0xFF1976DF),
                                        Color(0xFF0C4D96)
                                    ) else listOf(Color.Transparent, Color.Transparent)
                                )
                            )
                        )

                        if (settings.getString("ROL", "") == "administrador"){
                            NavigationBarItem(
                                selected = tabNavigator.current.key == StatsTab.key,
                                label = { Text(StatsTab.options.title, fontSize = 8.sp) },
                                icon = {
                                    Icon(
                                        painter = StatsTab.options.icon!!,
                                        contentDescription = null
                                    )
                                },
                                onClick = { tabNavigator.current = StatsTab },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color.White,
                                    unselectedIconColor = Color(0xFF1976DF),
                                    selectedTextColor = Color.White,
                                    unselectedTextColor = Color(0xFF1976DF),
                                    indicatorColor = Color.Transparent
                                ),
                                modifier = Modifier.clip(
                                    RoundedCornerShape(
                                        topStart = 10.dp,
                                        topEnd = 10.dp
                                    )
                                )
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = if (tabNavigator.current.key == StatsTab.key) listOf(
                                                Color(0xFF1976DF),
                                                Color(0xFF0C4D96)
                                            ) else listOf(Color.Transparent, Color.Transparent)
                                        )
                                    )
                            )
                        }
                    }
                },
            ) { innerPadding ->
                Column(
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF1976DF), Color(0xFF0C4D96)),
                            )
                        ),
                ) {
                    CurrentTab()
                }
            }
        }
        }
    }
    }
}