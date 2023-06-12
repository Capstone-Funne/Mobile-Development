package com.example.funne.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.funne.data.repository.FunneRepository
import okhttp3.MultipartBody

class ScanningViewModel(private val funneRepository: FunneRepository) : ViewModel() {
    suspend fun uploadImage(token: String, image: MultipartBody.Part) = funneRepository.uploadImage(token, image)
}
