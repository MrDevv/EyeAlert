package org.mrdevv.eyealert

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import eyealert.composeapp.generated.resources.Res
import eyealert.composeapp.generated.resources.logo
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.mrdevv.eyealert.login.data.AuthProviderImpl
import org.mrdevv.eyealert.login.domain.usecase.AuthProvider
import org.mrdevv.eyealert.login.presentation.components.ButtonCreateNewAccount
import org.mrdevv.eyealert.login.presentation.components.FormLogin
import org.mrdevv.eyealert.register.presentation.components.ButtonGoToLogin
import org.mrdevv.eyealert.register.presentation.components.ButtonRegister
import org.mrdevv.eyealert.register.presentation.components.FormRegister
import org.mrdevv.eyealert.ui.components.DividerFormsAuth
import org.mrdevv.eyealert.ui.components.Email
import org.mrdevv.eyealert.ui.components.LastName
import org.mrdevv.eyealert.ui.components.Name
import org.mrdevv.eyealert.ui.components.Password

class AuthScreen:Screen{

    @Composable
    override fun Content() {
        var showContentLogin by remember { mutableStateOf(true) }
        val navigator = LocalNavigator.currentOrThrow

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
                    FormLogin(navigator) { showContentLogin = it }
                } else {
                    FormRegister() { showContentLogin = it }
                }
            }
        }
    }


}