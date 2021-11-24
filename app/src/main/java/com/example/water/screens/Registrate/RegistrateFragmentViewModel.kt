package com.example.water.screens.Registrate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.water.models.UserData
import com.example.water.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrateFragmentViewModel(application: Application): AndroidViewModel(application) {

    fun registration(userData: UserData, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.registration(UserData(name = "", gender="", weight=50, normWater = 0)){
                onSuccess()
            }
        }
}