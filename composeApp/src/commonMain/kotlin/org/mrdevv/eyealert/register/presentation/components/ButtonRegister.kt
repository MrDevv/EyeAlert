package org.mrdevv.eyealert.register.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
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
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import org.mrdevv.eyealert.login.domain.usecase.AuthProvider
import org.mrdevv.eyealert.register.domain.dto.CreateUsuario
import org.mrdevv.eyealert.ui.components.Loader

@Composable
fun ButtonRegister(
    navigator: Navigator,
    authProvider: AuthProvider,
    name: String,
    lastname: String,
    email: String,
    password: String,
    onShowContentLoginChange: (Boolean) -> Unit,
    hideKeyBoard: () -> Unit,
    clearFields: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isLoading by remember { mutableStateOf(false) }

    var isEnableButton by remember { mutableStateOf(false) }

    if (name.isNotEmpty() && lastname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
        isEnableButton = true
    }else{
        isEnableButton = false
    }

    if (isLoading) {
        Loader(50)
    } else {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF344CC1),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF969fe1)
            ),
            enabled = isEnableButton,
            onClick = {
                isLoading = true
                hideKeyBoard()
                coroutineScope.launch {
                    val usuario = CreateUsuario(name, lastname, email, password)
                    authProvider.register(usuario) { userResponse ->
                        isLoading = false
                        coroutineScope.launch {
                            when {
                                userResponse == null -> snackbarHostState.showSnackbar("El servidor no está disponible. Inténtalo más tarde.")
                                userResponse.code == 201 && userResponse.userData != null -> {
                                    clearFields()
                                    snackbarHostState.showSnackbar(
                                        message = "Usuario creado. Ahora puedes iniciar sesión."
                                        )
                                    onShowContentLoginChange(true)
                                }
                            }
                        }
                    }
                }
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("REGISTRARSE", fontWeight = FontWeight.Bold)
            }
        }
        SnackbarHost(hostState = snackbarHostState)
    }
}