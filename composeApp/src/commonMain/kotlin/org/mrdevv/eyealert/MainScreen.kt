package org.mrdevv.eyealert

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import eyealert.composeapp.generated.resources.Res
import eyealert.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

class MainScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    it,
                    listOf(HomeTab, MyEvaluationsTab)
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                ambientColor = Color.Black.copy(alpha = 0.2f),
                                spotColor = Color.Black.copy(alpha = 0.3f)
                            ),
                        navigationIcon = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(Res.drawable.logo),
                                    contentDescription = "logo app"
                                )
                                Text("EyeAlert", color = Color.White, fontSize = 13.sp)
                            }
                        },
                        title = {},
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF1976DF)
                        ),
                        actions = {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "menu icon",
                                Modifier.size(30.dp),
                                tint = Color.White
                            )
                        }
                    )
                },
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier.height(65.dp),
                        containerColor = Color.White,
                        contentColor = Color(0xFF1976DF)
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
                            ).background(
                                brush = Brush.verticalGradient(
                                    colors = if (tabNavigator.current.key == StatsTab.key) listOf(
                                        Color(0xFF1976DF),
                                        Color(0xFF0C4D96)
                                    ) else listOf(Color.Transparent, Color.Transparent)
                                )
                            )
                        )
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