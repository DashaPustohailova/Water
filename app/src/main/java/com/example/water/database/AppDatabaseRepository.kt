package com.example.water.database

import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.water.R
import com.example.water.models.Report
import com.example.water.models.UserData
import com.example.water.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppDatabaseRepository : DatabaseRepository{


    val allReport: LiveData<List<Report>> = AllReportLiveData()

    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)}

        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(AUTH.currentUser?.uid.toString())
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
                        REF_DATABASE?.ref?.child("userData")
                            ?.push()?.key.toString()
                    val userDataNote = hashMapOf<String, Any>()
                    userDataNote[NAME] = userData.name
                    userDataNote[GENDER] = userData.gender
                    userDataNote[WEIGHT] = userData.weight

                    REF_DATABASE?.child("userData/${idUserData}")
                        ?.updateChildren(userDataNote)
                        ?.addOnSuccessListener { onSuccess()
                            Toast.makeText(APP_ACTIVITY, "add user data", Toast.LENGTH_SHORT).show()}
                },{})
                APP_ACTIVITY.mNavController.navigate(R.id.action_registrateFragment_to_lkFragment)
                onSuccess()
            }
    }
}