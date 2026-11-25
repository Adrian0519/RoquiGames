package com.example.myapplication.data.remote

import com.example.myapplication.data.model.LoginDTO
import com.example.myapplication.data.model.LoginRespuestaDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(@Body loginDTO: LoginDTO): Response<LoginRespuestaDTO>
}