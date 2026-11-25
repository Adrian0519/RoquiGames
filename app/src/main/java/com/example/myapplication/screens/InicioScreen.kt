package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.JuegoDestacado
import com.example.myapplication.data.model.Juego
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import com.example.myapplication.components.CarruselJuegos
import com.example.myapplication.components.LogoRoqui

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Row(verticalAlignment = Alignment.Bottom) {
            LogoRoqui()
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(stringResource(R.string.inicio_subtitulo), style = MaterialTheme.typography.bodyLarge)
        Text(
            stringResource(R.string.inicio_titulo),
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
            titulo = stringResource(R.string.inicio_recien_llegados),
            juegos = recienLlegados,
            textoEnlace = stringResource(R.string.inicio_ver_catalogo)
        )
    }
}

private val recienLlegados = listOf(
    Juego("Pokémon Esmeralda", "Game Boy Advance", "45,00 €", "8,00 €"),
    Juego("DOOM", "PC", "15,00 €", null),
    Juego("Sonic the Hedgehog", "Sega Mega Drive", "18,00 €", "3,00 €"),
    Juego("GTA: San Andreas", "PlayStation 2", "20,00 €", "4,00 €")
)