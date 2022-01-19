package com.example.water.presentation.screens.Registrate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.water.domain.usecase.SaveUserDataUseCase
import com.example.water.utilits.REPOSITORY

class RegistrationViewModelFactory : ViewModelProvider.Factory {

    private val saveUserDataUseCase by lazy{
        SaveUserDataUseCase(REPOSITORY)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegistrateFragmentViewModel(saveUserDataUseCase) as T
    }
}