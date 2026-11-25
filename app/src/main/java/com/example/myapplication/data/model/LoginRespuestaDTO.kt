package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class LoginRespuestaDTO(
    @SerializedName("id_usuario")
    val idUsuario: Int,
    val nick: String,
    val rol: String,
    val token: String
)