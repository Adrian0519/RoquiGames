package com.example.myapplication.viewmodel.uistate

import com.example.myapplication.data.model.LoginRespuestaDTO

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val cargando: Boolean = false,
    val error: String? = null,
    val usuario: LoginRespuestaDTO? = null
)