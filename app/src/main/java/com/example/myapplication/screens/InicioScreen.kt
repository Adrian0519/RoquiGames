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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recién llegados", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Ver catálogo →", style = MaterialTheme.typography.labelLarge, color = Color(0xFFC6F432))
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(recienLlegados) { juego ->
                TarjetaJuego(juego)
            }
        }
    }
}

@Composable
private fun JuegoDestacado(
    titulo: String,
    plataforma: String,
    anio: Int,
    precioCompra: String,
    precioAlquiler: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2B3A55)) // hueco para la imagen
                .padding(20.dp)
        ) {
            // Etiqueta arriba a la izquierda
            Text(
                text = "SELECCION ROQUI",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC6F432),
                modifier = Modifier.align(Alignment.TopStart)
            )

            // Datos abajo a la izquierda
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = titulo.uppercase(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "$plataforma / $anio".uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "$precioCompra · Alquila por $precioAlquiler",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC6F432)
                )
            }
        }
    }
}

@Composable
private fun TarjetaJuego(juego: JuegoResumen) {
    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Hueco de la imagen, con la plataforma encima
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF2B3A55))
                    .padding(8.dp)
            ) {
                Text(
                    text = juego.plataforma.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = juego.titulo,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = juego.precioCompra,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC6F432)
            )
        }
    }
}

data class JuegoResumen(
    val titulo: String,
    val plataforma: String,
    val precioCompra: String
)

private val recienLlegados = listOf(
    JuegoResumen("Pokémon Esmeralda", "Game Boy Advance", "45,00 €"),
    JuegoResumen("DOOM", "PC", "15,00 €"),
    JuegoResumen("Sonic the Hedgehog", "Sega Mega Drive", "18,00 €"),
    JuegoResumen("GTA: San Andreas", "PlayStation 2", "20,00 €")
)