package com.example.water.presentation.screens.lk

import androidx.lifecycle.ViewModel
import com.example.water.R
import com.example.water.data.utilits.*
import com.example.water.domain.models.Report
import com.example.water.domain.usecase.*
import com.example.water.utilits.*
import java.text.SimpleDateFormat
import java.util.*

class LkFragmentViewModel(
    private val signOutUseCase: SignOutUseCase,
    private val updateCountOfWaterUseCase: UpdateCountWaterUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val currentDataReportLiveData: GetCurrentReportUseCase,
    private val lastReportUseCase: GetLastReportUseCase,
    private val createCurrentDataReportUseCase: CreateCurrentDataReportUseCase


): ViewModel() {

    val userLiveData = getUserDataUseCase.execute()
    val currentReport = currentDataReportLiveData.execute()
    val lastReport = lastReportUseCase.execute()

    val sdf = SimpleDateFormat("dd.M.yyyy")

    fun signOut(){
        signOutUseCase.execute()
        APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_startFragment)
    }

    fun toStatisticFragment(){
        APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_statisticsFragment)
    }

    fun toPersonalDataFragment() {
        APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_userPersonalDataFragment)
    }

    fun changeCountWater(countWater: String) {
        updateCountOfWaterUseCase.execute(Report(sdf.format(Date()),countWater))
    }

    fun createCurrentDataReport(data: Long) {
        if (data == 0L) {
            //если в базе данных еще нет записи с текущей датой
            createCurrentDataReportUseCase.execute()
        }
    }

}