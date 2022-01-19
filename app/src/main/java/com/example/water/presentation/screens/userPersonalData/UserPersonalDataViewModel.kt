package com.example.water.presentation.screens.userPersonalData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.water.data.storage.firebase.UserDataLiveData
import com.example.water.domain.usecase.GetUserDataUseCase

class UserPersonalDataViewModel(
    private val getUserDataUseCase: GetUserDataUseCase
): ViewModel() {
    val userLiveData = getUserDataUseCase.execute()
}