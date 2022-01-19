package com.example.water.presentation.screens.lk

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.database.CurrentDateReportLiveData
import com.example.water.database.LastReportLiveData
import com.example.water.database.UserDataLiveData
import com.example.water.utilits.CURRENT_ID
import com.example.water.utilits.REF_DATABASE
import com.example.water.utilits.REPOSITORY

class LkFragmentViewModel(application: Application): AndroidViewModel(application) {

    val userLiveData = UserDataLiveData()
    val currentReport = CurrentDateReportLiveData()
    val lastReport = LastReportLiveData()

    fun signOut(){
        REPOSITORY.signOut()
    }

    fun selectReport(date: String): Boolean{
        return REF_DATABASE?.ref
            ?.child("report")?.child(CURRENT_ID)?.orderByChild("date")?.equalTo(date) != null
    }
//
//    fun initUser(){
//        REPOSITORY.initUser()
//    }
}