package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.myapplication.Components.CarruselJuegos
import com.example.myapplication.Components.JuegoDestacado
import com.example.myapplication.Components.TarjetaJuego
import com.example.myapplication.data.model.Juego

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                "roqui",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text("GAMES", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Tu tienda especializada en retro", style = MaterialTheme.typography.bodyLarge)
        Text(
            "Vuelve a darle al play.",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        JuegoDestacado(
            titulo = "Zelda: A Link to the Past",
            plataforma = "Super Nintendo",
            anio = 1991,
            precioCompra = "35,00 €",
            precioAlquiler = "7,00 €"
        )

        Spacer(modifier = Modifier.height(32.dp))

        CarruselJuegos(
            titulo = "Recién llegados",
            juegos = recienLlegados,
            textoEnlace = "Ver catálogo →"
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(recienLlegados) { juego ->
                TarjetaJuego(juego)
            }
        }
    }
}



private val recienLlegados = listOf(
    Juego("Pokémon Esmeralda", "Game Boy Advance", "45,00 €", "8,00 €"),
    Juego("DOOM", "PC", "15,00 €", null),
    Juego("Sonic the Hedgehog", "Sega Mega Drive", "18,00 €", "3,00 €"),
    Juego("GTA: San Andreas", "PlayStation 2", "20,00 €", "4,00 €")
)