package com.example.water.presentation.screens.Registrate

import android.widget.Toast
import androidx.lifecycle.*
import com.example.water.R
import com.example.water.domain.models.UserData
import com.example.water.domain.usecase.SaveUserDataUseCase
import com.example.water.utilits.APP_ACTIVITY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrateFragmentViewModel(
    private val saveUserDataUseCase: SaveUserDataUseCase
): ViewModel() {

    private val toastMessageMutableLiveData = MutableLiveData<String>()
    val toastMessageLiveData: LiveData<String> = toastMessageMutableLiveData


    fun registration(
        inputEmail: String,
        inputPassword: String,
        secondPassword: String,
        userData: UserData, ) {
        if (inputPassword.equals(secondPassword)) {
            viewModelScope.launch(Dispatchers.Main) {
                saveUserDataUseCase.execute(
                    inputEmail,
                    inputPassword,
                    UserData(
                        name = userData.name,
                        gender = userData.gender,
                        weight = userData.weight,
                        normWater = 0
                    ))
                    }
        }
        else {
            toastMessageMutableLiveData.value = "Пароли не совпадают"
        }
    }
}