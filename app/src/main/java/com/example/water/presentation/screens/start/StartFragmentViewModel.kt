package com.example.water.presentation.screens.start

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.water.R
import com.example.water.data.storage.firebase.CurrentDateReportLiveData
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.EMAIL
import com.example.water.utilits.PASSWORD
import com.example.water.utilits.REPOSITORY

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val context = application

    private val resultSignInMutableLiveData = MutableLiveData<String>()
    val resultSignInLiveData = resultSignInMutableLiveData
    val currentReport = CurrentDateReportLiveData()

    fun initDatabase(onSuccess: () -> Unit) {
        REPOSITORY.connectToDatabase({
            onSuccess() },
            { Toast.makeText(APP_ACTIVITY, "Проблемы при авторизации", Toast.LENGTH_SHORT).show() })
    }

    fun toRegistrateFragment() {
        APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)
    }

    fun signIn(inputEmail: String, inputPassword: String) {
        if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
            EMAIL = inputEmail
            PASSWORD = inputPassword
            initDatabase(){
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
