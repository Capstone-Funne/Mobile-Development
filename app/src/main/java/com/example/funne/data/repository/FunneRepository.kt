package com.example.funne.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.funne.data.model.AnalysisResponse
import com.example.funne.data.model.LoginResponse
import com.example.funne.data.model.ProfileResponse
import com.example.funne.data.model.ScanningResponse
import com.example.funne.data.network.ApiService
import com.example.funne.data.network.Result
import com.example.funne.data.request.AnalysisRequest
import com.example.funne.data.request.LoginRequest
import com.example.funne.data.request.RegisterRequest
import com.example.funne.data.result.GeneralResult
import okhttp3.MultipartBody

class FunneRepository(private val apiService: ApiService) {
    suspend fun login(loginRequest: LoginRequest): LiveData<Result<LoginResponse?>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(loginRequest)
            emit(Result.Success(response.loginResponse))
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "On Failure : ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun register(registerRequest: RegisterRequest): LiveData<Result<String?>> = liveData {
        emit(Result.Loading)
        val registerResponse = MutableLiveData<GeneralResult>()
        try {
            val response = apiService.register(registerRequest)
            registerResponse.postValue(response)
            emit(Result.Success(response.message))
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "On Failure : ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun getProfile(token: String): LiveData<Result<ProfileResponse?>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getProfile("Bearer $token")
            emit(Result.Success(response.profileResponse))
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "On Failure : ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun uploadImage(
        token: String,
        image: MultipartBody.Part,
    ): LiveData<Result<ScanningResponse?>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.uploadImage(token, image)
            emit(Result.Success(response.scanningResponse))
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "On Failure : ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun analyzeImage(
        token: String,
        analysisRequest: AnalysisRequest,
    ): LiveData<Result<List<AnalysisResponse?>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.analyzeImage(token, analysisRequest)
            emit(Result.Success(response.data.orEmpty()))
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "On Failure : ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: FunneRepository? = null

        fun getInstance(apiService: ApiService): FunneRepository =
            instance ?: synchronized(this) {
                instance ?: FunneRepository(apiService).also {
                    instance = it
                }
            }
    }
}
