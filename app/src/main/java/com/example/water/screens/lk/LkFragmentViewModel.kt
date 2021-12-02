package com.example.water.screens.lk

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.database.UserDataLiveData
import com.example.water.utilits.REPOSITORY

class LkFragmentViewModel(application: Application): AndroidViewModel(application) {

    val userLiveData = UserDataLiveData()

    fun signOut(){
        REPOSITORY.signOut()
    }
//
//    fun initUser(){
//        REPOSITORY.initUser()
//    }
}