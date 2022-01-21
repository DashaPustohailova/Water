package com.example.water.data.storage

import androidx.lifecycle.LiveData
import com.example.water.data.models.ReportStorage
import com.example.water.data.models.UserStorage
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData

interface webStorage {
    fun init()
    fun initUser()
    fun signOut()
    fun registration(inputEmail: String, inputPassword: String,userData: UserStorage)
    fun connectToDatabase(inputEmail: String, inputPassword: String, onSuccess: () -> Unit, onFail: () -> Unit)
    fun getAllReport() : LiveData<List<ReportStorage>>
    fun getCurrentDateReport() : LiveData<Long>
    fun getLastReport() : LiveData<ReportStorage>
    fun getUserData() : LiveData<UserStorage>
    fun updateUserData(report: ReportStorage)
    fun createCurrentDataReport()
}