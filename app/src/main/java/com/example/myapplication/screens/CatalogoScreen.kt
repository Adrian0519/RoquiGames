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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import com.example.myapplication.components.BarraBusqueda
import com.example.myapplication.data.model.Juego


private val juegosCatalogo = listOf(
    Juego("Super Mario Bros.", "NES", "25,00 €", "5,00 €"),
    Juego("Zelda: A Link to the Past", "Super Nintendo", "35,00 €", "7,00 €"),
    Juego("GTA: San Andreas", "PlayStation 2", "20,00 €", "4,00 €"),
    Juego("Sonic the Hedgehog", "Sega Mega Drive", "18,00 €", "3,00 €"),
    Juego("DOOM", "PC", "15,00 €", null),
    Juego("Pokémon Esmeralda", "Game Boy Advance", "45,00 €", "8,00 €")
)

@Composable
fun CatalogoScreen(modifier: Modifier = Modifier) {

    var busqueda by remember { mutableStateOf("") }

    val juegosFiltrados = juegosCatalogo.filter {
        it.titulo.contains(busqueda, ignoreCase = true)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text("Catálogo", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Elige tu próxima partida.",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                BarraBusqueda(
                    texto = busqueda,
                    onTextoChange = { busqueda = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        items(juegosFiltrados) { juego ->
            TarjetaCatalogo(juego)
        }
    }
}

@Composable
private fun TarjetaCatalogo(juego: Juego) {
    Card(shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Hueco de la imagen
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = juego.precioCompra,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC6F432)
                )
                Text("COMPRA", style = MaterialTheme.typography.labelSmall)
            }

            Text(
                text = if (juego.precioAlquiler != null) "Alquila ${juego.precioAlquiler} / día" else "Solo compra",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}