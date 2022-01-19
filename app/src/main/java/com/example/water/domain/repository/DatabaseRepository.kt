package com.example.water.domain.repository

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData

interface DatabaseRepository {
    fun connectToDatabase(inputEmail: String, inputPassword: String, onSuccess: () -> Unit, onFail: () -> Unit)
    fun signOut()
    fun initUser()
    fun registration(inputEmail: String, inputPassword: String, userData: UserData)
    fun getAllReport() : LiveData<List<Report>>
    fun getCurrentDateReport() : LiveData<Long>
    fun getLastReport() : LiveData<Report>
    fun getUserData() : LiveData<UserData>
    fun updateCountOfWater(report: Report)
}