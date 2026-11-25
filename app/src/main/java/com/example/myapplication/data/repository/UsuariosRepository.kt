package com.example.myapplication.data.repository

import com.example.myapplication.data.model.LoginDTO
import com.example.myapplication.data.model.LoginRespuestaDTO
import com.example.myapplication.data.remote.RetrofitClient

class UsuariosRepository {

    private val api = RetrofitClient.api

    suspend fun login(email: String, password: String): Result<LoginRespuestaDTO> {
        return try {
            val respuesta = api.login(LoginDTO(email, password))

            if (respuesta.isSuccessful && respuesta.body() != null) {
                Result.success(respuesta.body()!!)
            } else {
                Result.failure(Exception("Correo o contraseña incorrectos"))
            }
        } catch (e: Exception) {
            android.util.Log.e("LOGIN", "Error al conectar", e)
            Result.failure(Exception("No se puede conectar con el servidor"))
        }
    }
}