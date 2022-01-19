package com.example.water.presentation.screens.lk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.water.domain.usecase.SignOutUseCase
import com.example.water.domain.usecase.UpdateCountWaterUseCase
import com.example.water.utilits.REPOSITORY

class LkViewModelFactory: ViewModelProvider.Factory {

    private val signOutUseCase by lazy {
        SignOutUseCase(REPOSITORY)
    }

    private val updateCountOfWaterUseCase by lazy {
        UpdateCountWaterUseCase(REPOSITORY)
    }



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LkFragmentViewModel(
            signOutUseCase = signOutUseCase,
            updateCountOfWaterUseCase = updateCountOfWaterUseCase) as T
    }
}