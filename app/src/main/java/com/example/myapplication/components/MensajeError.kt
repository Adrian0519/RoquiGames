package com.example.myapplication.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

// Muestra el error, venga de strings.xml (errorRes) o de la API (error)
@Composable
fun MensajeError(error: String?, errorRes: Int?) {
    val mensaje = if (errorRes != null) stringResource(errorRes) else error

    if (mensaje != null) {
        Text(mensaje, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
    }
}