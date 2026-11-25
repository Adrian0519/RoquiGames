package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Sesion

//TODO: cambiar los text a string resources

@Composable
fun PerfilScreen(modifier: Modifier = Modifier) {
    val usuario = Sesion.usuario

    Column(modifier = modifier.fillMaxSize().padding(20.dp)) {
        Text("Perfil", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Hola, ${usuario?.nick}", style = MaterialTheme.typography.titleLarge)
        Text("Rol: ${usuario?.rol}", color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = { Sesion.cerrar() }) {
            Text("Cerrar sesión")
        }
    }
}