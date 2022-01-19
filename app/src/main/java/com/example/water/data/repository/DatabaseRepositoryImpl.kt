package com.example.water.data.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.water.R
import com.example.water.data.storage.webStorage
import com.example.water.domain.models.Report
import com.example.water.domain.repository.DatabaseRepository
import com.example.water.domain.models.UserData
import com.example.water.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
        webStorage.registration(inputEmail = inputEmail,inputPassword = inputPassword, userData = userData)
    }

    override fun getAllReport(): LiveData<List<Report>> {
        return webStorage.getAllReport()
    }

    override fun getCurrentDateReport(): LiveData<Long> {
        return webStorage.getCurrentDateReport()
    }

    override fun getLastReport(): LiveData<Report> {
        return webStorage.getLastReport()
    }

    override fun getUserData(): LiveData<UserData> {
        return webStorage.getUserData()
    }

    override fun updateCountOfWater(report: Report) {
        webStorage.updateUserData(report = report)
    }
}