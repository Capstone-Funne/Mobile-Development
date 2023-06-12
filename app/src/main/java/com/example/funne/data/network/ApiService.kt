package com.example.funne.data.network

import com.example.funne.data.request.AnalysisRequest
import com.example.funne.data.request.LoginRequest
import com.example.funne.data.request.RegisterRequest
import com.example.funne.data.result.AnalysisResult
import com.example.funne.data.result.GeneralResult
import com.example.funne.data.result.ProfileResult
import com.example.funne.data.result.ScanningResult
import com.example.funne.data.result.SuggestionResult
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): GeneralResult

    @Headers("Content-Type: application/json")
    @POST("api/v1/users")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): GeneralResult

    @Headers("Content-Type: application/json")
    @GET("api/v1/users/me")
    suspend fun getProfile(
        @Header("Authorization") token: String,
    ): ProfileResult

    @Multipart
    @POST("api/v1/visions/images")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
    ): ScanningResult

    @Headers("Content-Type: application/json")
    @POST("/api/v1/ingredients/analyze")
    suspend fun analyzeImage(
        @Header("Authorization") token: String,
        @Body analysisRequest: AnalysisRequest,
    ): AnalysisResult

    @Headers("Content-Type: application/json")
    @GET("/api/v1/solutions")
    suspend fun solution(
        @Header("Authorization") token: String,
    ): SuggestionResult
}
