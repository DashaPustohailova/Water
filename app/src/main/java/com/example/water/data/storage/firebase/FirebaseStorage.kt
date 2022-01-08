package com.example.water.data.storage.firebase

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.water.R
import com.example.water.data.storage.webStorage
import com.example.water.domain.models.Report
import com.example.water.domain.models.UserData
import com.example.water.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FirebaseStorage : webStorage {

    override fun init(){
        AUTH = FirebaseAuth.getInstance()
    }

    override fun initUser() {
        REF_DATABASE?.ref
            ?.child("users")?.child(CURRENT_ID)
            ?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    USER_DATA = snapshot.getValue(UserData::class.java)?: UserData()
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    override fun signOut() {
        AUTH.signOut()
        CURRENT_ID = ""
        ID_REPORT = ""
        USER_DATA = UserData()
    }

    override fun registration(userData: UserData) {
        AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                connectToDatabase(
                    onSuccess = {
                        val userDataNote = hashMapOf<String, Any>()
                        userDataNote[NAME] = userData.name
                        userDataNote[GENDER] = userData.gender
                        userDataNote[WEIGHT] = userData.weight
                        if(userData.gender.equals("Женский")){
                            userDataNote[NORM_WATER] = userData.weight.toInt() * 31
                            Log.d("Value", userData.weight.toString())
                        }
                        else {
                            userDataNote[NORM_WATER] = userData.weight.toInt() * 35
                            Log.d("Value", userData.weight.toString())
                        }

                        REF_DATABASE?.child("users/${AUTH.currentUser?.uid.toString()}")
                            ?.updateChildren(userDataNote)
                            ?.addOnSuccessListener {
//                                Toast.makeText(APP_ACTIVITY, "add user data", Toast.LENGTH_SHORT).show()
                            }
                        Toast.makeText(APP_ACTIVITY, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                        APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_lkFragment)
                    },
                    onFail = {
                        Toast.makeText(APP_ACTIVITY, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
                    })
            }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                CURRENT_ID = AUTH.currentUser?.uid.toString()
                REF_DATABASE = FirebaseDatabase.getInstance().reference
                onSuccess()
            }
            .addOnFailureListener {
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)}
    }

    override fun getAllReport() : LiveData<List<Report>> {
        return AllReportLiveData()
    }

    override fun getCurrentDateReport(): LiveData<Long> {
        return CurrentDateReportLiveData()
    }

    override fun getLastReport(): LiveData<Report> {
        return LastReportLiveData()
    }

    override fun getUserData(): LiveData<UserData> {
        return UserDataLiveData()
    }


}