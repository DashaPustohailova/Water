package com.example.water.presentation.screens.lk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.water.domain.usecase.*
import com.example.water.utilits.REPOSITORY

class LkViewModelFactory: ViewModelProvider.Factory {

    private val signOutUseCase by lazy {
        SignOutUseCase(REPOSITORY)
    }

    private val updateCountOfWaterUseCase by lazy {
        UpdateCountWaterUseCase(REPOSITORY)
    }


    private val getUserDataUseCase by lazy {
        GetUserDataUseCase(REPOSITORY)
    }


    private val currentDateReportUseCase by lazy {
        GetCurrentReportUseCase(REPOSITORY)
    }


    private val lastReportUseCase by lazy {
        GetLastReportUseCase(REPOSITORY)
    }


    private val createCurrentDataReportUseCase by lazy {
        CreateCurrentDataReportUseCase(REPOSITORY)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LkFragmentViewModel(
            signOutUseCase = signOutUseCase,
            updateCountOfWaterUseCase = updateCountOfWaterUseCase,
            getUserDataUseCase = getUserDataUseCase,
            currentDataReportLiveData = currentDateReportUseCase,
            lastReportUseCase = lastReportUseCase,
            createCurrentDataReportUseCase = createCurrentDataReportUseCase
        ) as T
    }
}