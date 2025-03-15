package org.mrdevv.eyealert

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mrdevv.eyealert.home.presentation.screen.HomeScreen

val settings : Settings = Settings()

@Composable
@Preview
fun App() {
    MaterialTheme {
        val idUser = settings.getLong("ID", 0)
        if (idUser.toInt() !=0){
            Navigator(screen = MainScreen())
        }else{
            Navigator(screen =  AuthScreen())
        }
    }
}