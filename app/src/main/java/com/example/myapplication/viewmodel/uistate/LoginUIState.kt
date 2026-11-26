package com.example.myapplication.viewmodel.uistate

import androidx.annotation.StringRes
import com.example.myapplication.data.model.LoginRespuestaDTO

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val cargando: Boolean = false,
    val error: String? = null,
    @StringRes val errorRes: Int? = null,
    val usuario: LoginRespuestaDTO? = null
)