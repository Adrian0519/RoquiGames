package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.MensajeException
import com.example.myapplication.data.Sesion
import com.example.myapplication.data.model.RegistrarUsuarioDTO
import com.example.myapplication.data.repository.UsuariosRepository
import com.example.myapplication.viewmodel.uistate.RegistroUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {

    private val repository = UsuariosRepository()

    private val _uiState = MutableStateFlow(RegistroUIState())
    val uiState: StateFlow<RegistroUIState> = _uiState.asStateFlow()

    fun cambiarNick(valor: String) = _uiState.update { it.copy(nick = valor, error = null, errorRes = null) }
    fun cambiarNombre(valor: String) = _uiState.update { it.copy(nombre = valor, error = null, errorRes = null) }
    fun cambiarApellidos(valor: String) = _uiState.update { it.copy(apellidos = valor, error = null, errorRes = null) }
    fun cambiarEmail(valor: String) = _uiState.update { it.copy(email = valor, error = null, errorRes = null) }
    fun cambiarPassword(valor: String) = _uiState.update { it.copy(password = valor, error = null, errorRes = null) }
    fun cambiarRepetirPassword(valor: String) = _uiState.update { it.copy(repetirPassword = valor, error = null, errorRes = null) }

    fun registrar() {
        val estado = _uiState.value

        if (estado.nick.isBlank() || estado.nombre.isBlank() || estado.email.isBlank() || estado.password.isBlank()) {
            _uiState.update { it.copy(errorRes = R.string.registro_error_vacios) }
            return
        }

        if (estado.password != estado.repetirPassword) {
            _uiState.update { it.copy(errorRes = R.string.registro_error_no_coinciden) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null, errorRes = null) }

            val dto = RegistrarUsuarioDTO(
                nick = estado.nick.trim(),
                nombre = estado.nombre.trim(),
                apellidos = estado.apellidos.trim(),
                email = estado.email.trim(),
                password = estado.password
            )

            val resultadoRegistro = repository.registrar(dto)

            resultadoRegistro.onFailure { e ->
                mostrarError(e)
                return@launch
            }

            repository.login(dto.email, dto.password)
                .onSuccess { usuario ->
                    Sesion.iniciar(usuario)
                    _uiState.update { it.copy(cargando = false, registrado = true) }
                }
                .onFailure { e -> mostrarError(e) }
        }
    }

    private fun mostrarError(e: Throwable) {
        if (e is MensajeException) {
            _uiState.update { it.copy(cargando = false, errorRes = e.mensaje) }
        } else {
            _uiState.update { it.copy(cargando = false, error = e.message) }
        }
    }
}