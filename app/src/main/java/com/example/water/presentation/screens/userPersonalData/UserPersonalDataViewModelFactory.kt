package com.example.water.presentation.screens.userPersonalData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.water.domain.usecase.GetUserDataUseCase
import com.example.water.utilits.REPOSITORY

class UserPersonalDataViewModelFactory : ViewModelProvider.Factory {

    private val getUserDataUseCase by lazy {
        GetUserDataUseCase(REPOSITORY)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserPersonalDataViewModel(getUserDataUseCase) as T
    }
}