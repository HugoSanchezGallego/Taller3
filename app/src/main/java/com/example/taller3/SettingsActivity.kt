package com.example.taller3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taller3.ui.theme.Taller3Theme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

class SettingsActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        setContent {
            Taller3Theme {
                PantallaConfiguracion(sharedPreferences) { navigateToStart() }
            }
        }
    }

    private fun navigateToStart() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun PantallaConfiguracion(sharedPreferences: SharedPreferences, onNavigate: () -> Unit) {
    var colorFondo by remember { mutableStateOf(Color(sharedPreferences.getInt("color_fondo", Color.White.toArgb()))) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).background(colorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            colorFondo = Color.Red
            sharedPreferences.edit().putInt("color_fondo", Color.Red.toArgb()).apply()
        }) {
            Text("Cambiar fondo a rojo")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            colorFondo = Color.Green
            sharedPreferences.edit().putInt("color_fondo", Color.Green.toArgb()).apply()
        }) {
            Text("Cambiar fondo a verde")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            colorFondo = Color.Blue
            sharedPreferences.edit().putInt("color_fondo", Color.Blue.toArgb()).apply()
        }) {
            Text("Cambiar fondo a azul")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigate) {
            Text("Volver al inicio")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaConfiguracionPrevia() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    Taller3Theme {
        PantallaConfiguracion(sharedPreferences) {}
    }
}