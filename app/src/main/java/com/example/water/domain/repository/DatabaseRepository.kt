package com.example.water.domain.repository

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData

interface DatabaseRepository {
    fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit)
    fun signOut()
    fun initUser()
    fun registration(userData: UserData)
    fun getAllReport() : LiveData<List<Report>>
    fun getCurrentDateReport() : LiveData<Long>
    fun getLastReport() : LiveData<Report>
    fun getUserData() : LiveData<UserData>
}