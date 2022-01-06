package com.example.water.data.repository

import android.util.Log
import android.widget.Toast
import com.example.water.R
import com.example.water.data.storage.webStorage
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

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {

                CURRENT_ID = AUTH.currentUser?.uid.toString()
                REF_DATABASE = FirebaseDatabase.getInstance().reference

                onSuccess() }
            .addOnFailureListener {
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)}
    }

    override fun signOut() {
        AUTH.signOut()
        CURRENT_ID = ""
        ID_REPORT = ""
        USER_DATA = UserData()
    }

    override fun registration(userData: UserData, onSuccess: () -> Unit ) {

        AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                connectToDatabase(
                    {
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
                        ?.addOnSuccessListener { onSuccess()
                            Toast.makeText(APP_ACTIVITY, "add user data", Toast.LENGTH_SHORT).show()}

                },{})
                Toast.makeText(APP_ACTIVITY, "Регистрация прошла успешна", Toast.LENGTH_SHORT).show()
                APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_startFragment)
                onSuccess()
            }
    }

}