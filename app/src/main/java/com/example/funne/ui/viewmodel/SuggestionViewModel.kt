package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.repository.FunneRepository

class SuggestionViewModel(private val funneRepository: FunneRepository) : ViewModel() {
    suspend fun solution(token: String) = funneRepository.solution(token)
}
