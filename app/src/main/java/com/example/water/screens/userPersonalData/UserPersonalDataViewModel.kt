package com.example.water.screens.userPersonalData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.database.UserDataLiveData

class UserPersonalDataViewModel(application: Application): AndroidViewModel(application) {
    val userLiveData = UserDataLiveData()
}