package org.mrdevv.eyealert.login.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import cafe.adriel.voyager.core.stack.popUntil
import cafe.adriel.voyager.navigator.Navigator
import com.russhwolf.settings.Settings
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.MainScreen
import org.mrdevv.eyealert.login.domain.usecase.AuthProvider
import org.mrdevv.eyealert.ui.components.Loader

private val settings: Settings = Settings()

@Composable
fun ButtonLogin(navigator: Navigator, email: String, password: String, authProvider: AuthProvider, hideKeyBoard: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isLoading by remember { mutableStateOf(false) }


    var isEnableButton by remember { mutableStateOf(false) }

    if (email.isNotEmpty() && password.isNotEmpty()){
        isEnableButton = true
    }else{
        isEnableButton = false
    }

    if (isLoading){
        Loader(50)
    }else{
        Button(modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976DF),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF969fe1)
            ),
//            enabled = isEnableButtonValue,
            enabled = isEnableButton,
            onClick = {
                hideKeyBoard()
                isLoading = true
                coroutineScope.launch {
                    authProvider.signIn(email, password) { userResponse ->
//                    println(userResponse?.code)
//                    println(userResponse?.userData)
//                    println(userResponse)
                        isLoading = false

                        if (userResponse == null){
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("El servidor no se encuentra disponible en estos momentos, intentalo más tarde.")
                            }
                        }

                        if (userResponse?.code == 200 && userResponse.userData != null){
                            settings.putString("NAME", userResponse.userData.nombres)
                            settings.putLong("ID", userResponse.userData.id)
                            settings.putString("ROL", userResponse.userData.rol)
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


}