package com.example.water.presentation.screens.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.water.data.repository.DatabaseRepositoryImpl
import com.example.water.data.storage.firebase.FirebaseStorage
import com.example.water.domain.usecase.GetAllReportUseCase
import com.example.water.utilits.REPOSITORY

class StatisticViewModelFactory : ViewModelProvider.Factory {


//    private val repository by lazy {
//        DatabaseRepositoryImpl(webStorage = FirebaseStorage())
//    }

    private val getAllReportUseCase by lazy {
        GetAllReportUseCase(repository = REPOSITORY)
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatisticksFragmentViewModel(
            getAllReportUseCase = getAllReportUseCase
        ) as T
    }
}