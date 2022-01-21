package com.example.water.presentation.screens.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.water.data.storage.firebase.AllReportLiveData
import com.example.water.domain.usecase.GetAllReportUseCase
import com.example.water.domain.usecase.GetUserDataUseCase
import com.example.water.utilits.REPOSITORY

class StatisticksFragmentViewModel(
    private val getAllReportUseCase: GetAllReportUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
): ViewModel() {

    val userData = getUserDataUseCase.execute()
    val allReport = getAllReportUseCase.execute()

}