package com.example.composenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenavigationapp.ui.theme.ComposeNavigationAppTheme
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController = navController)
        }
        composable("screen_b?message={message}") {
            val message = it.arguments?.getString("message")
            ScreenB(navController = navController, message = message)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "o que você deseja ver?")
        Button(onClick = { navController.navigate("screen_b?message=Qual seu favorito?") },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource( id = R.color.azul))) {
            Text("personagens de Dexter")
        }
        Button(onClick = { navController.navigate("screen_b") },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource( id = R.color.azul))) {
            Text("perfis oficiais")
        }
    }
}

@Composable
fun ScreenB(navController: NavController, message: String?) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "PERSONAGENS:")
        Text(text = "Dexter Morgan")
        Text(text = "Debra Morgan")
        Text(text = "Angel Batista")
        Text(text = "Brian Mooser")
        Text(text = "Joey Quinn")
        Text(text = "Rita Morgan")
        Text(text = "Vince Masuka")
        message?.let { Text(text = "tela A:  $it") }
        Button(onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource( id = R.color.azul))) {
            Text("Voltar")
        }
    }
}

// Pré-visualizações (opcional)
@Preview(showBackground = true)
@Composable
fun ScreenAPreview() {
    ComposeNavigationAppTheme {
        ScreenA(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBPreview() {
    ComposeNavigationAppTheme {
        ScreenB(rememberNavController(), "Qual seu favorito?")
    }
}