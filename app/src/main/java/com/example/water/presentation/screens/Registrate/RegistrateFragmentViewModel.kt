package com.example.water.presentation.screens.Registrate

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.water.domain.models.UserData
import com.example.water.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrateFragmentViewModel(application: Application): AndroidViewModel(application) {


    fun registration(userData: UserData) =
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.registration(UserData(name = userData.name, gender=userData.gender, weight=userData.weight, normWater = 0))
        }
}