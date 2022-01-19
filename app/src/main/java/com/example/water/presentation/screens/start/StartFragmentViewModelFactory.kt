package com.example.water.presentation.screens.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.water.domain.usecase.GetAllReportUseCase
import com.example.water.domain.usecase.InitDatabaseUseCase
import com.example.water.utilits.REPOSITORY

private val initDatabaseUseCase by lazy {
    InitDatabaseUseCase(repository = REPOSITORY)
}

class StartFragmentViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StartFragmentViewModel(initDatabaseUseCase) as T
    }
}