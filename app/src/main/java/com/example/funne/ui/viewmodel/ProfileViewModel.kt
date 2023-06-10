package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.repository.FunneRepository

class ProfileViewModel(private val funneRepository: FunneRepository): ViewModel() {
    suspend fun getProfile(token: String) = funneRepository.getProfile(token)
}