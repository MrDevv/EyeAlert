package org.mrdevv.eyealert.login.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import org.mrdevv.eyealert.login.data.AuthProviderImpl
import org.mrdevv.eyealert.ui.components.DividerFormsAuth
import org.mrdevv.eyealert.ui.components.Email
import org.mrdevv.eyealert.ui.components.Password

@Composable
fun FormLogin(navigator: Navigator, onShowContentLoginChange: (Boolean) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authProvider = AuthProviderImpl()

    val keyboardController = LocalSoftwareKeyboardController.current

    Text("INICIAR SESIÓN", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(5.dp))
    Text("Por favor inicia sesión para continuar.")
    Spacer(Modifier.height(15.dp))
    Email(email) { email = it }
    Spacer(Modifier.height(5.dp))
    Password(password) { password = it }
    Spacer(Modifier.height(10.dp))
    ButtonLogin(navigator, email, password, authProvider){
        keyboardController?.hide()
    }
    DividerFormsAuth()
    ButtonCreateNewAccount(onShowContentLoginChange)
}