package com.example.water.domain.usecase

import com.example.water.domain.repository.DatabaseRepository

class InitDatabaseUseCase (private val repository: DatabaseRepository) {
    fun execute(inputEmail: String, inputPassword: String, onSuccess: () -> Unit, onFail: () -> Unit)  {
        return repository.connectToDatabase(
            inputEmail = inputEmail,
            inputPassword = inputPassword,
            onSuccess  = {
                onSuccess()
            },
            onFail = {
                onFail()
            }
        )
    }
}