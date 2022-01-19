package com.example.water.presentation.screens.start

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.water.R
import com.example.water.data.storage.firebase.CurrentDateReportLiveData
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.REPOSITORY

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val resultSignInMutableLiveData = MutableLiveData<String>()
    val resultSignInLiveData : LiveData<String> = resultSignInMutableLiveData

    fun initDatabase(inputEmail: String, inputPassword: String, onSuccess: () -> Unit) {
        REPOSITORY.connectToDatabase(
            inputEmail = inputEmail,
            inputPassword = inputPassword,
            onSuccess = {
                onSuccess()
            },
            onFail = {
                resultSignInMutableLiveData.value = "Проблемы при авторизации"
            }
        )
    }

    fun toRegistrateFragment() {
        APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)
    }

    fun signIn(inputEmail: String, inputPassword: String) {
        if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
            initDatabase(inputEmail, inputPassword){
                //если инициализация прошла успешно
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_lkFragment)
                resultSignInMutableLiveData.value = "Инициализация прошла успешно"
            }
        }
        else{
            resultSignInMutableLiveData.value = "Неверный логин или пароль"
        }
    }


}
