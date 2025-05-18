package org.mrdevv.eyealert.register.presentation.components

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
import org.mrdevv.eyealert.ui.components.LastName
import org.mrdevv.eyealert.ui.components.Name
import org.mrdevv.eyealert.ui.components.Password

@Composable
fun FormRegister(navigator: Navigator, onShowContentLoginChange: (Boolean) -> Unit) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isValidEmail by remember { mutableStateOf(true) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val authProvider = AuthProviderImpl()

    fun clearFields() {
        name = ""
        lastName = ""
        email = ""
        password = ""
    }

    Text("REGISTRARSE", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(5.dp))
    Text("Por favor completa tus datos para continuar.")
    Spacer(Modifier.height(15.dp))
    Name(name) { name = it }
    Spacer(Modifier.height(5.dp))
    LastName(lastName) { lastName = it }
    Spacer(Modifier.height(5.dp))
    Email(email, isValidEmail, onValidEmailChange = { isValidEmail = it }) { email = it }
    Spacer(Modifier.height(5.dp))
    Password(password) { password = it }
    Spacer(Modifier.height(10.dp))
    ButtonRegister(navigator, authProvider, name, lastName, email, isValidEmail, password, onShowContentLoginChange, keyboardController!!::hide, ::clearFields)
    DividerFormsAuth()
    ButtonGoToLogin(onShowContentLoginChange)
}
