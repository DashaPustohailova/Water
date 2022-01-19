package com.example.water.presentation.screens.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.water.data.storage.firebase.AllReportLiveData
import com.example.water.domain.usecase.GetAllReportUseCase
import com.example.water.utilits.REPOSITORY

class StatisticksFragmentViewModel(
    private val getAllReportUseCase: GetAllReportUseCase
): ViewModel() {

    val allReport = getAllReportUseCase.execute()

}