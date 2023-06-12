package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.repository.FunneRepository
import com.example.funne.data.request.AnalysisRequest

class AnalysisViewModel(private val funneRepository: FunneRepository) : ViewModel() {
    suspend fun analyzeImage(token: String, analysisRequest: AnalysisRequest) = funneRepository.analyzeImage(token, analysisRequest)
}