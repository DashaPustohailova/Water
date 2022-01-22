package com.example.water.domain.usecase

import com.example.water.domain.models.UserData
import com.example.water.domain.repository.DatabaseRepository

class SaveUserDataUseCase(private val repository: DatabaseRepository) {

    fun execute( inputEmail: String, inputPassword: String, userData: UserData, onSuccess: () -> Unit, onFail: () -> Unit){
        repository.registration(
            inputEmail = inputEmail,
            inputPassword = inputPassword,
            userData = userData,
            onSuccess = {
                onSuccess()
            },
            onFail = {
                onFail()
            }
        )
    }
}