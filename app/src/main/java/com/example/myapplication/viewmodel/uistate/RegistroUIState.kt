package com.example.myapplication.viewmodel.uistate

import androidx.annotation.StringRes

data class RegistroUIState(
    val nick: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val email: String = "",
    val password: String = "",
    val repetirPassword: String = "",
    val cargando: Boolean = false,
    val error: String? = null,
    @StringRes val errorRes: Int? = null,
    val registrado: Boolean = false
)