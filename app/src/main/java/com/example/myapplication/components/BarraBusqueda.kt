package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

@Composable
fun BarraBusqueda(
    texto: String,
    onTextoChange: (String) -> Unit,
    placeholder: String = stringResource(R.string.buscar_placeholder)
) {
    OutlinedTextField(
        value = texto,
        onValueChange = onTextoChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = stringResource(R.string.buscar)) },
        singleLine = true,
        shape = RoundedCornerShape(16.dp)
    )
}