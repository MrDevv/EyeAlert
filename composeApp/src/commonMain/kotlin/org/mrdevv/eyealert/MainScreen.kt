package org.mrdevv.eyealert

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                        title = {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        modifier = Modifier.size(24.dp),
                                        painter = painterResource(Res.drawable.logo),
                                        contentDescription = "logo app"
                                    )
                                    Text("EyeAlert", color = Color.White, fontSize = 13.sp)
                                }
                                Icon(
                                    Icons.Filled.Menu,
                                    contentDescription = "menu icon",
                                    Modifier.size(30.dp),
                                    tint = Color.White
                                )
                            }
                        },
                        modifier = Modifier.background(Color(0xFF1976DF))
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = Color.White,
                        contentColor = Color(0xFF1976DF)
                    ) {
                        val tabNavigator: TabNavigator = LocalTabNavigator.current

                        NavigationBarItem(
                            selected = tabNavigator.current.key == HomeTab.key,
                            label = { Text(HomeTab.options.title) },
                            icon = {
                                Icon(
                                    painter = HomeTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = { tabNavigator.current = HomeTab },
                            colors = NavigationBarItemDefaults.colors()
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == MyEvaluationsTab.key,
                            label = { Text(MyEvaluationsTab.options.title) },
                            icon = {
                                Icon(
                                    painter = MyEvaluationsTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = { tabNavigator.current = MyEvaluationsTab }
                        )
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}