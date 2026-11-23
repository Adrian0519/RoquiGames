package com.example.myapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val RoquiColorScheme = darkColorScheme(
    primary = RoquiLima,
    onPrimary = RoquiFondo,
    background = RoquiFondo,
    onBackground = RoquiTexto,
    surface = RoquiFondo,
    onSurface = RoquiTexto,
    surfaceVariant = RoquiSuperficie,
    onSurfaceVariant = RoquiTextoSecundario,
    surfaceContainerHighest = RoquiSuperficie
)

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RoquiColorScheme,
        typography = Typography,
        content = content
    )
}