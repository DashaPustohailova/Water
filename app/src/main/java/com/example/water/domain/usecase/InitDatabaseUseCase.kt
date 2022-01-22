package com.example.water.domain.usecase

import android.util.Log
import com.example.water.domain.repository.DatabaseRepository

class InitDatabaseUseCase (private val repository: DatabaseRepository) {
    fun execute(inputEmail: String, inputPassword: String, onSuccess: () -> Unit, onFail: () -> Unit)  {
        repository.connectToDatabase(
            inputEmail = inputEmail,
            inputPassword = inputPassword,
            onSuccess  = {
                onSuccess()
            },
            onFail = {
                Log.d("VALUE", "eeee")
                onFail()
            }
        )
    }
}