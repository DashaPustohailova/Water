package com.example.water.data.storage.firebase

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.water.R
import com.example.water.data.models.ReportStorage
import com.example.water.data.models.UserStorage
import com.example.water.data.storage.webStorage
import com.example.water.data.utilits.*
import com.example.water.domain.models.Report
import com.example.water.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*


class FirebaseStorage : webStorage {
    private lateinit var AUTH: FirebaseAuth
    val sdf = SimpleDateFormat("dd.M.yyyy")

    override fun init(){
        AUTH = FirebaseAuth.getInstance()
    }

    override fun initUser() {
        REF_DATABASE?.ref
            ?.child("users")?.child(CURRENT_ID)
            ?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    USER_DATA_STORAGE = snapshot.getValue(UserStorage::class.java)?: UserStorage()
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    override fun signOut() {
        AUTH.signOut()
        CURRENT_ID = ""
        ID_REPORT = ""
        USER_DATA_STORAGE = UserStorage()
    }

    override fun registration(inputEmail: String, inputPassword: String, userData: UserStorage, onSuccess: () -> Unit, onFail: () -> Unit) {
        AUTH.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnSuccessListener {
                connectToDatabase(inputEmail, inputPassword,
                    onSuccess = {
                        val userDataNote = hashMapOf<String, Any>()
                        userDataNote["name"] = userData.name
                        userDataNote["gender"] = userData.gender
                        userDataNote["weight"] = userData.weight
                        if(userData.gender.equals("Женский")){
                            userDataNote["normWater"] = userData.weight.toInt() * 31
                            Log.d("Value", userData.weight.toString())
                        }
                        else {
                            userDataNote["normWater"] = userData.weight.toInt() * 35
                            Log.d("Value", userData.weight.toString())
                        }

                        REF_DATABASE?.child("users/${AUTH.currentUser?.uid.toString()}")
                            ?.updateChildren(userDataNote)
                            ?.addOnSuccessListener {
                            }
                        onSuccess()
                    },
                    onFail = {
                        onFail()
                    })
            }
    }

    override fun connectToDatabase(inputEmail: String, inputPassword: String,onSuccess: () -> Unit, onFail: () -> Unit) {
        AUTH.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnSuccessListener {
                CURRENT_ID = AUTH.currentUser?.uid.toString()
                REF_DATABASE = FirebaseDatabase.getInstance().reference
                Log.d("Value", "sign in")
                onSuccess()
            }
            .addOnFailureListener {
                onFail()
            }
    }

    override fun getAllReport() : LiveData<List<ReportStorage>> {
        return AllReportLiveData()
    }

    override fun getCurrentDateReport(): LiveData<Long> {
        return CurrentDateReportLiveData()
    }

    override fun getLastReport(): LiveData<ReportStorage> {
        return LastReportLiveData()
    }

    override fun getUserData(): LiveData<UserStorage> {
        return UserDataLiveData()
    }

    override fun updateUserData(report: ReportStorage) {
        val reportNote = hashMapOf<String, Any>()
        reportNote["date"] =  report.date
        reportNote["water"] = report.water

        REF_DATABASE?.child("report/$CURRENT_ID/$ID_REPORT")
            ?.updateChildren(reportNote)
            ?.addOnSuccessListener {
            }
    }

    override fun createCurrentDataReport() {
        val idReport  = REF_DATABASE?.ref
            ?.child("report/$CURRENT_ID")
            ?.push()?.key.toString()
        val reportNote = hashMapOf<String, Any>()
        val report = Report(date = sdf.format(Date()), water = "0")
        reportNote["date"] =  report.date
        reportNote["water"] = report.water
        REF_DATABASE?.child("report/$CURRENT_ID/$idReport")
            ?.updateChildren(reportNote)
            ?.addOnSuccessListener {
            }
    }


}