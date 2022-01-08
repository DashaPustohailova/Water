package com.example.water.presentation.screens.lk

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.R
import com.example.water.data.storage.firebase.CurrentDateReportLiveData
import com.example.water.data.storage.firebase.LastReportLiveData
import com.example.water.data.storage.firebase.UserDataLiveData
import com.example.water.domain.models.Report
import com.example.water.utilits.*
import java.text.SimpleDateFormat
import java.util.*

class LkFragmentViewModel(application: Application): AndroidViewModel(application) {

    val userLiveData = REPOSITORY.getUserData()
    val currentReport = REPOSITORY.getCurrentDateReport()
    val lastReport = REPOSITORY.getLastReport()

    val sdf = SimpleDateFormat("dd.M.yyyy")

    fun signOut(){
        REPOSITORY.signOut()
        APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_startFragment)
    }

    fun toStatisticFragment(){
        APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_statisticsFragment)
    }

    fun toPersonalDataFragment() {
        APP_ACTIVITY.mNavController.navigate(R.id.action_lkFragment_to_userPersonalDataFragment)
    }

    fun changeCountWater(countWater: String) {
        val reportNote = hashMapOf<String, Any>()
        val report = Report(sdf.format(Date()),"0")
        reportNote[DATE] =  report.date
        reportNote[WATER] = countWater
        REF_DATABASE?.child("report/$CURRENT_ID/$ID_REPORT")
            ?.updateChildren(reportNote)
            ?.addOnSuccessListener {
            }
    }

    fun createCurrentDataReport(data: Long) {
        if (data == 0L) {
            //если в базе данных еще нет записи с текущей датой
            val idReport  = REF_DATABASE?.ref
                ?.child("report/$CURRENT_ID")
                ?.push()?.key.toString()
            val reportNote = hashMapOf<String, Any>()
            val report = Report(date = sdf.format(Date()), water = "0")
            reportNote[DATE] =  report.date
            reportNote[WATER] = report.water
            REF_DATABASE?.child("report/$CURRENT_ID/$idReport")
                ?.updateChildren(reportNote)
                ?.addOnSuccessListener {
                }
        }
    }

}