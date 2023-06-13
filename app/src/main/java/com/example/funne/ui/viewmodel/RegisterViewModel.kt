package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.repository.FunneRepository
import com.example.funne.data.request.RegisterRequest

class RegisterViewModel(private val funneRepository: FunneRepository) : ViewModel() {
    suspend fun register(registerRequest: RegisterRequest) = funneRepository.register(registerRequest)
}