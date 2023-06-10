package com.example.funne.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.funne.data.model.GeneralResponse
import com.example.funne.data.model.LoginResponse
import com.example.funne.data.model.ProfileResponse
import com.example.funne.data.network.ApiService
import com.example.funne.data.network.Result
import com.example.funne.data.request.LoginRequest
import com.example.funne.data.request.RegisterRequest

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
        val registerResponse = MutableLiveData<GeneralResponse>()
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
        try{
            val response = apiService.getProfile("Bearer $token")
            emit(Result.Success(response))
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
