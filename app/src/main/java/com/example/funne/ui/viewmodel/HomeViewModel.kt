package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.repository.FunneRepository

class HomeViewModel (private val funneRepository: FunneRepository) : ViewModel() {
    suspend fun product(token: String) = funneRepository.product(token)
    suspend fun getProfile(token: String) = funneRepository.getProfile(token)
}