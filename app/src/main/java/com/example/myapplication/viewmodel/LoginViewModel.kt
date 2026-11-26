package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.MensajeException
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

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun cambiarEmail(nuevo: String) {
        _uiState.update { it.copy(email = nuevo, error = null, errorRes = null) }
    }

    fun cambiarPassword(nueva: String) {
        _uiState.update { it.copy(password = nueva, error = null, errorRes = null) }
    }

    fun iniciarSesion() {
        val estado = _uiState.value

        if (estado.email.isBlank() || estado.password.isBlank()) {
            _uiState.update { it.copy(errorRes = R.string.login_error_vacios) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null, errorRes = null) }

            repository.login(estado.email.trim(), estado.password)
                .onSuccess { usuario ->
                    Sesion.iniciar(usuario)
                    _uiState.update { it.copy(cargando = false, usuario = usuario) }
                }
                .onFailure { e ->
                    if (e is MensajeException) {
                        _uiState.update { it.copy(cargando = false, errorRes = e.mensaje) }
                    } else {
                        _uiState.update { it.copy(cargando = false, error = e.message) }
                    }
                }
        }
    }
}