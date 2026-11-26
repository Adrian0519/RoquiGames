package com.example.myapplication.data.repository

import com.example.myapplication.R
import com.example.myapplication.data.MensajeException
import com.example.myapplication.data.model.ErrorDTO
import com.example.myapplication.data.model.LoginDTO
import com.example.myapplication.data.model.LoginRespuestaDTO
import com.example.myapplication.data.model.PerfilUsuarioDTO
import com.example.myapplication.data.model.RegistrarUsuarioDTO
import com.example.myapplication.data.remote.RetrofitClient
import com.google.gson.Gson

class UsuariosRepository {

    private val api = RetrofitClient.api

    suspend fun login(email: String, password: String): Result<LoginRespuestaDTO> {
        return try {
            val respuesta = api.login(LoginDTO(email, password))

            if (respuesta.isSuccessful && respuesta.body() != null) {
                Result.success(respuesta.body()!!)
            } else {
                Result.failure(MensajeException(R.string.login_error_credenciales))
            }
        } catch (e: Exception) {
            Result.failure(MensajeException(R.string.error_conexion))
        }
    }

    suspend fun registrar(registrarUsuarioDTO: RegistrarUsuarioDTO): Result<PerfilUsuarioDTO> {
        return try {
            val respuesta = api.registrar(registrarUsuarioDTO)

            if (respuesta.isSuccessful && respuesta.body() != null) {
                Result.success(respuesta.body()!!)
            } else if (respuesta.code() >= 500) {
                Result.failure(MensajeException(R.string.error_servidor))
            } else {
                val mensajeApi = leerError(respuesta.errorBody()?.string())
                if (mensajeApi != null) {
                    Result.failure(Exception(mensajeApi))
                } else {
                    Result.failure(MensajeException(R.string.error_generico))
                }
            }
        } catch (e: Exception) {
            Result.failure(MensajeException(R.string.error_conexion))
        }
    }

    // Saca el mensaje de un error de la API: {"error": "..."}. Si no hay, devuelve null
    private fun leerError(json: String?): String? {
        return try {
            Gson().fromJson(json, ErrorDTO::class.java)?.error
        } catch (e: Exception) {
            null
        }
    }
}