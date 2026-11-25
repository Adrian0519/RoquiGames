package com.example.myapplication.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.data.model.LoginRespuestaDTO

object Sesion {

    var usuario by mutableStateOf<LoginRespuestaDTO?>(null)
        private set

    val token: String?
        get() = usuario?.token

    fun iniciar(usuarioLogueado: LoginRespuestaDTO) {
        usuario = usuarioLogueado
    }

    fun cerrar() {
        usuario = null
    }
}