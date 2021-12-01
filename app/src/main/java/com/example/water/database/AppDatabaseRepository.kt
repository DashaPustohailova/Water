package com.example.water.database

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.water.R
import com.example.water.models.Report
import com.example.water.models.UserData
import com.example.water.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppDatabaseRepository : DatabaseRepository{


    val allReport: LiveData<List<Report>> = AllReportLiveData()

    init {
        AUTH = FirebaseAuth.getInstance()
    }
    override  fun initUser(){
        REF_DATABASE?.ref
            ?.child("users")?.child(CURRENT_ID)
            ?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    USER_DATA= snapshot.getValue(UserData::class.java)!!
                    Log.d("Value", USER_DATA.name.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }
    override fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                initUser()
                onSuccess() }
            .addOnFailureListener {
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)}

        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
//            .child(AUTH.currentUser?.uid.toString())
    }

    override fun signOut() {
        AUTH.signOut()
    }

    override fun registration(userData: UserData, onSuccess: () -> Unit ) {

        AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                connectToDatabase(
                    {

                    val idUserData =
                        REF_DATABASE?.ref?.child("users/${AUTH.currentUser?.uid.toString()}")
                            ?.push()?.key.toString()
                    val userDataNote = hashMapOf<String, Any>()
                    userDataNote[NAME] = userData.name
                    userDataNote[GENDER] = userData.gender
                    userDataNote[WEIGHT] = userData.weight

                    REF_DATABASE?.child("users/${AUTH.currentUser?.uid.toString()}")
                        ?.updateChildren(userDataNote)
                        ?.addOnSuccessListener { onSuccess()
                            Toast.makeText(APP_ACTIVITY, "add user data", Toast.LENGTH_SHORT).show()}
                },{})
                APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_lkFragment)
                onSuccess()
            }
    }
}