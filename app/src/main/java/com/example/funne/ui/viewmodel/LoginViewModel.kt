package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.model.LoginResponse
import com.example.funne.data.repository.FunneRepository
import com.example.funne.data.request.LoginRequest

class LoginViewModel(private val funneRepository: FunneRepository) : ViewModel() {
    suspend fun login(loginRequest: LoginRequest) = funneRepository.login(loginRequest)
}
