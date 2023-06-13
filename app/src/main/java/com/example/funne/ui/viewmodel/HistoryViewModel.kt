package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.repository.FunneRepository

class HistoryViewModel(private val funneRepository: FunneRepository) : ViewModel() {
    suspend fun history(token: String) = funneRepository.history(token)
}
