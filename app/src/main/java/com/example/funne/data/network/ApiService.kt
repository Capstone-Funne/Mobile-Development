package com.example.funne.data.network

import com.example.funne.data.model.GeneralResponse
import com.example.funne.data.request.LoginRequest
import com.example.funne.data.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): GeneralResponse

    @Headers("Content-Type: application/json")
    @POST("api/v1/users")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): GeneralResponse
}
