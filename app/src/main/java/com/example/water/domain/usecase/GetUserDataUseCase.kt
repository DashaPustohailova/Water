package com.example.water.domain.usecase

import androidx.lifecycle.LiveData
import com.example.water.domain.models.UserData
import com.example.water.domain.repository.DatabaseRepository

class GetUserDataUseCase(private val repository: DatabaseRepository) {
    fun execute() : LiveData<UserData>{
        return repository.getUserData()
    }
}