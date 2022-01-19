package com.example.water.presentation.screens.lk

import androidx.lifecycle.ViewModel
import com.example.water.R
import com.example.water.data.utilits.*
import com.example.water.domain.models.Report
import com.example.water.domain.usecase.SignOutUseCase
import com.example.water.domain.usecase.UpdateCountWaterUseCase
import com.example.water.utilits.*
import java.text.SimpleDateFormat
import java.util.*

class LkFragmentViewModel(
    private val signOutUseCase: SignOutUseCase,
    private val updateCountOfWaterUseCase: UpdateCountWaterUseCase
): ViewModel() {

    val userLiveData = REPOSITORY.getUserData()
    val currentReport = REPOSITORY.getCurrentDateReport()
    val lastReport = REPOSITORY.getLastReport()

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
            val idReport  = REF_DATABASE?.ref
                ?.child("report/$CURRENT_ID")
                ?.push()?.key.toString()
            val reportNote = hashMapOf<String, Any>()
            val report = Report(date = sdf.format(Date()), water = "0")
            reportNote["date"] =  report.date
            reportNote["water"] = report.water
            REF_DATABASE?.child("report/$CURRENT_ID/$idReport")
                ?.updateChildren(reportNote)
                ?.addOnSuccessListener {
                }
        }
    }

}