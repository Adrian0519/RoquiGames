package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Sesion
import com.example.myapplication.data.repository.UsuariosRepository
import com.example.myapplication.viewmodel.uistate.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = UsuariosRepository()

    // Estado de la pantalla: privado y modificable dentro del ViewModel…
    private val _uiState = MutableStateFlow(LoginUIState())

    // …y público, pero solo de lectura, para la pantalla
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun cambiarEmail(nuevo: String) {
        _uiState.update { it.copy(email = nuevo, error = null) }
    }

    fun cambiarPassword(nueva: String) {
        _uiState.update { it.copy(password = nueva, error = null) }
    }

    fun iniciarSesion() {
        val estado = _uiState.value

        if (estado.email.isBlank() || estado.password.isBlank()) {
            _uiState.update { it.copy(error = "Introduce el correo y la contraseña") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }

            repository.login(estado.email.trim(), estado.password)
                .onSuccess { usuario ->
                    Sesion.iniciar(usuario)
                    _uiState.update { it.copy(cargando = false, usuario = usuario) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(cargando = false, error = e.message) }
                }
        }
    }
}