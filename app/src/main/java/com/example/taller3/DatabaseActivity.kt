package com.example.taller3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.taller3.ui.theme.Taller3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseActivity : ComponentActivity() {
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "database-name")
            .fallbackToDestructiveMigration()
            .build()
        userDao = db.userDao()
        setContent {
            Taller3Theme {
                PantallaBaseDatos(userDao)
            }
        }
    }
}

@Composable
fun PantallaBaseDatos(userDao: UserDao) {
    var usuarios by remember { mutableStateOf(listOf<User>()) }

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            usuarios = userDao.getAllUsers()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        usuarios.forEach { user ->
            Text(text = "ID: ${user.uid}, Nombre: ${user.nombre}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                userDao.deleteAll()
                usuarios = userDao.getAllUsers()
            }
        }) {
            Text("Vaciar Base de Datos")
        }
    }
}