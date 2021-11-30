package com.example.water.screens.Registrate

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.water.models.UserData
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrateFragmentViewModel(application: Application): AndroidViewModel(application) {

    fun initDatabase(onSuccess: () -> Unit) {
        REPOSITORY.connectToDatabase({ onSuccess() },
            { Toast.makeText(APP_ACTIVITY, "Проблемы при авторизации", Toast.LENGTH_SHORT).show() })
    }

    fun registration(userData: UserData, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.registration(UserData(name = userData.name, gender=userData.gender, weight=userData.weight, normWater = 0)){
                onSuccess()
            }
        }
}