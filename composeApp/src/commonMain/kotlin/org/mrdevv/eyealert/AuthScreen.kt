package org.mrdevv.eyealert

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eyealert.composeapp.generated.resources.Res
import eyealert.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun AuthScreen() {
    var showContentLogin by remember { mutableStateOf(true) }

    Column(
        Modifier.fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1976DF), Color(0xFF000000)),
                )
            )
    ) {
//        CONTENEDOR LOGO APP
        Column(
            Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(Res.drawable.logo),
                contentDescription = "logo_aplication",
                modifier = Modifier.size(80.dp)
            )
            Text("EyeAlert", color = Color.White, fontSize = 25.sp)
        }
//        CONTENEDOR FORMULARIOS - LOGIN / REGISTER
        Column(
            Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 25.dp,
                        topEnd = 25.dp
                    )
                )
                .background(Color.White)
                .padding(horizontal = 30.dp, vertical = 40.dp)
        ) {
            if (showContentLogin) {
                FormLogin() { showContentLogin = it }
            } else {
                FormRegister() { showContentLogin = it }
            }
        }
    }
}

@Composable
fun FormRegister(onShowContentLoginChange: (Boolean) -> Unit) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Text("REGISTRARSE", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(5.dp))
    Text("Por favor completa tus datos para continuar.")
    Spacer(Modifier.height(15.dp))
    Name(name) { name = it }
    Spacer(Modifier.height(5.dp))
    LastName(lastName) { lastName = it }
    Spacer(Modifier.height(5.dp))
    Email(email) { email = it }
    Spacer(Modifier.height(5.dp))
    Password(password) { password = it }
    Spacer(Modifier.height(10.dp))
    ButtonRegister()
    DividerFormsAuth()
    ButtonGoToLogin(onShowContentLoginChange)
}

@Composable
fun LastName(lastName: String, onLastNameChange: (String) -> Unit) {
    TextField(
        lastName,
        onValueChange = { onLastNameChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Apellidos",
                color = Color(0xFF464646),
                fontWeight = FontWeight.ExtraLight
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFE3E3E3)
        )
    )
}

@Composable
fun Name(name: String, onNameChange: (String) -> Unit) {
    TextField(
        name,
        onValueChange = { onNameChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Nombres",
                color = Color(0xFF464646),
                fontWeight = FontWeight.ExtraLight
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFE3E3E3)
        )
    )
}

@Composable
fun ButtonGoToLogin(onShowContentLoginChange: (Boolean) -> Unit) {
    OutlinedButton(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color(0xFF1976DF)
        ),
        border = BorderStroke(2.dp, Color(0xFF1976DF)),
        onClick = {
            onShowContentLoginChange(true)
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "icono login",
                tint = Color.White
            )
            Text("IR A INICIAR SESIÓN", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ButtonRegister() {
    Button(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF344CC1),
            contentColor = Color.White
        ),
        onClick = {}
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "icono login",
                tint = Color.White
            )
            Text("REGISTRARSE", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FormLogin(onShowContentLoginChange: (Boolean) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Text("INICIAR SESIÓN", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(5.dp))
    Text("Por favor inicia sesión para continuar.")
    Spacer(Modifier.height(15.dp))
    Email(email) { email = it }
    Spacer(Modifier.height(5.dp))
    Password(password) { password = it }
    Spacer(Modifier.height(10.dp))
    ButtonLogin()
    ButtonLoginWithGoogle()
    DividerFormsAuth()
    ButtonCreateNewAccount(onShowContentLoginChange)
}

@Composable
fun ButtonCreateNewAccount(onShowContentLoginChange: (Boolean) -> Unit) {
    OutlinedButton(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color(0xFF344CC1),
        ),
        border = BorderStroke(2.dp, Color(0xFF344CC1)),
        onClick = {
            onShowContentLoginChange(false)
        }
    ) {
        Text("CREAR NUEVA CUENTA", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun DividerFormsAuth() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier.fillMaxWidth().weight(1f))
        Text(
            "ó",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Divider(modifier = Modifier.fillMaxWidth().weight(1f))
    }
}

@Composable
fun ButtonLoginWithGoogle() {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF2B425C),
            contentColor = Color.White
        ), onClick = {}) {
        Text("INICIAR SESIÓN CON GOOGLE", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ButtonLogin() {
    Button(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF1976DF),
            contentColor = Color.White
        ),
        onClick = {}) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "icono login",
                tint = Color.White
            )
            Text("INICIAR SESIÓN", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun Password(password: String, onPasswordChange: (String) -> Unit) {
    TextField(
        password,
        onValueChange = { onPasswordChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Contraseña",
                color = Color(0xFF464646),
                fontWeight = FontWeight.ExtraLight
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFE3E3E3)
        )
    )
}

@Composable
fun Email(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        email,
        onValueChange = { onEmailChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Correo electrónico",
                color = Color(0xFF464646),
                fontWeight = FontWeight.ExtraLight
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFE3E3E3)
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "icon email")
        }
    )
}