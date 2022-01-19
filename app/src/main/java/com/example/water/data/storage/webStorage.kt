package com.example.water.data.storage

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData

interface webStorage {
    fun init()
    fun initUser()
    fun signOut()
    fun registration(inputEmail: String, inputPassword: String,userData: UserData)
    fun connectToDatabase(inputEmail: String, inputPassword: String, onSuccess: () -> Unit, onFail: () -> Unit)
    fun getAllReport() : LiveData<List<Report>>
    fun getCurrentDateReport() : LiveData<Long>
    fun getLastReport() : LiveData<Report>
    fun getUserData() : LiveData<UserData>
    fun updateUserData(report: Report)
    fun createCurrentDataReport()
}