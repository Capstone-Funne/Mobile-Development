package com.example.funne.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.funne.data.repository.FunneRepository
import com.example.funne.ui.viewmodel.LoginViewModel
import com.example.funne.ui.viewmodel.ProfileViewModel
import com.example.funne.ui.viewmodel.RegisterViewModel

class ViewModelFactory private constructor(private val funneRepository: FunneRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(funneRepository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(funneRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(funneRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: ViewModelFactory(Injection.provideRepository(context)).also { INSTANCE = it }
    }
}
