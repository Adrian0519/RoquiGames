package com.example.myapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.myapplication.components.TarjetaCatalogo
import com.example.myapplication.components.BarraBusqueda
import com.example.myapplication.data.model.Juego
import com.example.myapplication.R


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
                Text(stringResource(R.string.catalogo_titulo), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    stringResource(R.string.catalogo_subtitulo),
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

