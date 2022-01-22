package com.example.water.presentation.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.water.R
import com.example.water.domain.usecase.InitDatabaseUseCase
import com.example.water.utilits.APP_ACTIVITY

class StartFragmentViewModel(
    private val initDatabaseUseCase: InitDatabaseUseCase
): ViewModel() {
    private val resultSignInMutableLiveData = MutableLiveData<String>()
    val resultSignInLiveData : LiveData<String> = resultSignInMutableLiveData

    fun initDatabase(inputEmail: String, inputPassword: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        initDatabaseUseCase.execute(
            inputEmail = inputEmail,
            inputPassword = inputPassword,
            onSuccess = {
                onSuccess()
            },
            onFail = {
                resultSignInMutableLiveData.value = "Проблемы при авторизации"
                onFail()
            }

        )
    }

    fun toRegistrateFragment() {
        APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)
    }

    fun signIn(inputEmail: String, inputPassword: String) {
        if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
            initDatabase(
                inputEmail = inputEmail,
                inputPassword = inputPassword,
                onSuccess = {
                    //если инициализация прошла успешно
                    APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_lkFragment)
                    resultSignInMutableLiveData.value = "Инициализация прошла успешно"
                },
                onFail = {
                    APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)
                    resultSignInMutableLiveData.value = "Аккаунт не существует"
                }
            )
        }
        else{
            resultSignInMutableLiveData.value = "Неверный логин или пароль"
        }
    }


}
