package com.example.water.presentation.screens.Registrate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD:app/src/main/java/com/example/water/presentation/screens/Registrate/RegistrateFragmentViewModel.kt
import com.example.water.models.UserData
=======
import com.example.water.domain.models.UserData
>>>>>>> 04a74eaed8119723aecfe8c70e062fa68d6061cb:app/src/main/java/com/example/water/screens/Registrate/RegistrateFragmentViewModel.kt
import com.example.water.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrateFragmentViewModel(application: Application): AndroidViewModel(application) {


    fun registration(userData: UserData, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.registration(UserData(name = userData.name, gender=userData.gender, weight=userData.weight, normWater = 0)){
                onSuccess()
            }
        }
}