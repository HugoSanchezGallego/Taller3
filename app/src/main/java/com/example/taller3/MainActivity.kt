package com.example.taller3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.taller3.ui.theme.Taller3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val db = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "database-name")
            .fallbackToDestructiveMigration()
            .build()
        userDao = db.userDao()
        setContent {
            Taller3Theme {
                val context = LocalContext.current
                PantallaPrincipal(sharedPreferences, userDao, context) {
                    startActivity(Intent(this, StartActivity::class.java))
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(sharedPreferences: SharedPreferences, userDao: UserDao, context: Context, onNavigate: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var nombreGuardado by remember { mutableStateOf(sharedPreferences.getString("nombre", "") ?: "") }
    var colorFondo by remember { mutableStateOf(Color(sharedPreferences.getInt("color_fondo", Color.White.toArgb()))) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(colorFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Ingresa tu nombre") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            sharedPreferences.edit().putString("nombre", nombre).apply()
            nombreGuardado = nombre
            CoroutineScope(Dispatchers.IO).launch {
                userDao.insertAll(User(uid = nombre.hashCode(), nombre = nombre))
            }
        }) {
            Text("Guardar Nombre")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nombre guardado: $nombreGuardado")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigate) {
            Text("Volver a la pantalla de inicio")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }) {
            Text("Ir a Ajustes")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            context.startActivity(Intent(context, DatabaseActivity::class.java))
        }) {
            Text("Ver Base de Datos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPrevia() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val db = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java).build()
    val userDao = db.userDao()
    Taller3Theme {
        PantallaPrincipal(sharedPreferences, userDao, context) {}
    }
}