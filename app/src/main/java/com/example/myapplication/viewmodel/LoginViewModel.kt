package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.LoginRespuestaDTO
import com.example.myapplication.data.repository.UsuariosRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = UsuariosRepository()

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var cargando by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var usuario by mutableStateOf<LoginRespuestaDTO?>(null)
        private set

    // ---- Acciones que llama la pantalla ----
    fun cambiarEmail(nuevo: String) {
        email = nuevo
        error = null
    }

    fun cambiarPassword(nueva: String) {
        password = nueva
        error = null
    }

    fun iniciarSesion() {
        if (email.isBlank() || password.isBlank()) {
            error = "Introduce el correo y la contraseña"
            return
        }

        viewModelScope.launch {
            cargando = true
            error = null

            val resultado = repository.login(email.trim(), password)

            resultado
                .onSuccess { usuario = it }
                .onFailure { error = it.message }

            cargando = false
        }
    }
}