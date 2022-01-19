package com.example.water.presentation.screens.userPersonalData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.data.storage.firebase.UserDataLiveData

class UserPersonalDataViewModel(application: Application): AndroidViewModel(application) {
    val userLiveData = UserDataLiveData()
}