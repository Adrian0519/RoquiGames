package com.example.myapplication.data.remote

import com.example.myapplication.data.model.LoginDTO
import com.example.myapplication.data.model.LoginRespuestaDTO
import com.example.myapplication.data.model.PerfilUsuarioDTO
import com.example.myapplication.data.model.RegistrarUsuarioDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(@Body loginDTO: LoginDTO): Response<LoginRespuestaDTO>

    @POST("api/usuarios/registro")
    suspend fun registrar(@Body registrarUsuarioDTO: RegistrarUsuarioDTO): Response<PerfilUsuarioDTO>
}