package com.example.water.data.storage

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData

interface webStorage {
    fun init()
    fun initUser()
    fun signOut()
    fun registration(userData: UserData)
    fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit)
    fun getAllReport() : LiveData<List<Report>>
    fun getCurrentDateReport() : LiveData<Long>
    fun getLastReport() : LiveData<Report>
    fun getUserData() : LiveData<UserData>
}