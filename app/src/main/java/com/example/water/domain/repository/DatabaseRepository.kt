package com.example.water.domain.repository

import com.example.water.domain.models.UserData

interface DatabaseRepository {
    fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit)
    fun signOut()
    fun initUser()
    fun registration(userData: UserData, onSuccess: () -> Unit)
}