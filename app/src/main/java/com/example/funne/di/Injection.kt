package com.example.funne.di

import android.content.Context
import com.example.funne.data.network.ApiConfig
import com.example.funne.data.repository.FunneRepository

object Injection {
    fun provideRepository(context: Context): FunneRepository {
        val apiService = ApiConfig.getApiService()
        return FunneRepository.getInstance(apiService)
    }
}
