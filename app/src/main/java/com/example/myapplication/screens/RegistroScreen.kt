package com.example.myapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.MensajeError
import com.example.myapplication.viewmodel.RegistroViewModel

@Composable
fun RegistroScreen(
    modifier: Modifier = Modifier,
    viewModel: RegistroViewModel = viewModel(),
    onIrALogin: () -> Unit = {},
    onRegistrado: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    var verPassword by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.registrado) {
        if (uiState.registrado) onRegistrado()
    }

    val transformacion = if (verPassword) VisualTransformation.None else PasswordVisualTransformation()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(stringResource(R.string.registro_etiqueta), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        Text(stringResource(R.string.registro_titulo), style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Text(
            stringResource(R.string.registro_subtitulo),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        CampoRegistro(uiState.nick, { viewModel.cambiarNick(it) }, stringResource(R.string.registro_nick))
        CampoRegistro(uiState.nombre, { viewModel.cambiarNombre(it) }, stringResource(R.string.registro_nombre))
        CampoRegistro(uiState.apellidos, { viewModel.cambiarApellidos(it) }, stringResource(R.string.registro_apellidos))
        CampoRegistro(
            uiState.email, { viewModel.cambiarEmail(it) }, stringResource(R.string.login_email),
            teclado = KeyboardType.Email
        )
        CampoRegistro(
            uiState.password, { viewModel.cambiarPassword(it) }, stringResource(R.string.login_password),
            teclado = KeyboardType.Password, transformacion = transformacion,
            iconoFinal = {
                TextButton(onClick = { verPassword = !verPassword }) {
                    Text(stringResource(if (verPassword) R.string.login_ocultar else R.string.login_ver))
                }
            }
        )
        CampoRegistro(
            uiState.repetirPassword, { viewModel.cambiarRepetirPassword(it) }, stringResource(R.string.registro_repetir_password),
            teclado = KeyboardType.Password, transformacion = transformacion
        )

        MensajeError(error = uiState.error, errorRes = uiState.errorRes)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.registrar() },
            enabled = !uiState.cargando,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            if (uiState.cargando) {
                CircularProgressIndicator(modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
            } else {
                Text(stringResource(R.string.registro_boton), fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.registro_ya_tienes_cuenta), color = MaterialTheme.colorScheme.onSurfaceVariant)
            TextButton(onClick = onIrALogin) {
                Text(stringResource(R.string.registro_inicia_sesion), fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Un campo de texto del formulario.
@Composable
private fun CampoRegistro(
    valor: String,
    onCambio: (String) -> Unit,
    etiqueta: String,
    teclado: KeyboardType = KeyboardType.Text,
    transformacion: VisualTransformation = VisualTransformation.None,
    iconoFinal: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta) },
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        visualTransformation = transformacion,
        trailingIcon = iconoFinal,
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    )
}