package org.mrdevv.eyealert.login.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.stack.popUntil
import cafe.adriel.voyager.navigator.Navigator
import com.russhwolf.settings.Settings
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.MainScreen
import org.mrdevv.eyealert.login.domain.usecase.AuthProvider

private val settings: Settings = Settings()

@Composable
fun ButtonLogin(navigator: Navigator, email: String, password: String, authProvider: AuthProvider) {
    val coroutineScope = rememberCoroutineScope() // Para manejar las corrutinas en Composable
    val snackbarHostState = remember { SnackbarHostState() } // Estado del Snackbar

    Button(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1976DF),
            contentColor = Color.White
        ),
        onClick = {
            coroutineScope.launch {
                authProvider.signIn(email, password) { userResponse ->
//                    println(userResponse?.code)
//                    println(userResponse?.userData)
//                    println(userResponse)

                    if (userResponse?.code == 200 && userResponse.userData != null){
                        settings.putString("NAME", userResponse.userData.nombres)
                        settings.putLong("ID", userResponse.userData.id)
                        navigator.replaceAll(MainScreen())
                    }else if (userResponse?.code == 200 && userResponse.userData == null){
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Credenciales incorrectas. Inténtalo de nuevo.")
                        }
                    }
                }
            }

//            navigator.replaceAll(MainScreen())

        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("INICIAR SESIÓN", fontWeight = FontWeight.Bold)
        }
    }
    SnackbarHost(hostState = snackbarHostState)
}