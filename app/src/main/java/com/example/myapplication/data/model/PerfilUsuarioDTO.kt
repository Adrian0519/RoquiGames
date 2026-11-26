package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class PerfilUsuarioDTO(
    @SerializedName("id_usuario")
    val idUsuario: Int,
    val nombre: String,
    val apellidos: String?,
    val email: String,
    val nick: String
)