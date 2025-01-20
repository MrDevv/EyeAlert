package org.mrdevv.eyealert

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AuthScreen()
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth().background(Color.Red), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.logo), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
    }
}