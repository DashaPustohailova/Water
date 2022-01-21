package com.example.water.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.water.data.models.ReportStorage
import com.example.water.data.models.UserStorage
import com.example.water.data.storage.webStorage
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData
import com.example.water.domain.repository.DatabaseRepository
import com.example.water.utilits.*


class DatabaseRepositoryImpl(private val webStorage: webStorage) : DatabaseRepository {

    init {
        webStorage.init()
    }

    override fun initUser(){
        webStorage.initUser()
    }

    override fun connectToDatabase(inputEmail: String, inputPassword: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        webStorage.connectToDatabase(
            inputEmail = inputEmail,
            inputPassword = inputPassword,
            onSuccess ={
               onSuccess()
            },
            onFail = {}
        )
    }

    override fun signOut() {
        webStorage.signOut()
    }

    override fun registration(inputEmail: String, inputPassword: String,userData: UserData) {
        webStorage.registration(inputEmail = inputEmail,inputPassword = inputPassword, userData = userDataToStorage(userData))
    }

    override fun getAllReport(): LiveData<List<Report>> {
        return getAllReportLiveDataToDomain()
    }

    override fun getCurrentDateReport(): LiveData<Long> {
        return webStorage.getCurrentDateReport()
    }

    override fun getLastReport(): LiveData<Report> {
        return lastReportLiveDataToDomain()
    }

    override fun getUserData(): LiveData<UserData> {
        return userLiveDataToDomain()
    }

    override fun updateCountOfWater(report: Report) {
        webStorage.updateUserData(report = reportToStorage(report))
    }

    override fun createCurrentDataReport() {
        webStorage.createCurrentDataReport()
    }

    fun userLiveDataToDomain(): LiveData<UserData>{
        val userStorageLiveData = webStorage.getUserData()
        val userLiveDataDomain: LiveData<UserData> = Transformations.map(userStorageLiveData)
        {user ->
            val user = userStorageLiveData.value
            val name = user?.name
            val gender = user?.gender
            val weight = user?.weight
            val normWater = user?.normWater
            UserData(
                name = name?:"",
                gender = gender?:"",
                weight = weight?:0,
                normWater = normWater?:0)
        }
        return userLiveDataDomain
    }

    fun lastReportLiveDataToDomain(): LiveData<Report>{
        val lastReportLiveData = webStorage.getLastReport()
        val lastReportDomain: LiveData<Report> = Transformations.map(lastReportLiveData)
        {report ->
            val reportData = lastReportLiveData.value
            val date = reportData?.date
            val water = reportData?.water
            Report(
                date = date?:"",
                water = water?:""
            )
        }
        return lastReportDomain
    }

    fun getAllReportLiveDataToDomain() : LiveData<List<Report>>{
        val listAllReport = webStorage.getAllReport()
        val listAllReportDomain: LiveData<List<Report>> = Transformations.map(listAllReport)
        {report ->
            report.map{
                Report(date = it.date, water = it.water)
            }
        }
        return listAllReportDomain
    }

    fun userDataToStorage(user: UserData) :  UserStorage{
        return UserStorage(
            name = user.name,
            gender = user.gender,
            weight = user.weight,
            normWater = user.normWater
        )
    }

    fun reportToStorage(report: Report) : ReportStorage{
        return ReportStorage(
            date = report.date,
            water = report.water
        )
    }

}