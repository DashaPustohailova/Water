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
import kotlinx.android.synthetic.main.fragment_lk.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppDatabaseRepository() : DatabaseRepository{

    val allReport: LiveData<List<Report>> = AllReportLiveData()

    init {
        AUTH = FirebaseAuth.getInstance()
    }
    override fun initUser(){
        REF_DATABASE?.ref
            ?.child("users")?.child(CURRENT_ID)
            ?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    USER_DATA = snapshot.getValue(UserData::class.java)?: UserData()
                    Log.d("Value", CURRENT_ID + USER_DATA.name.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }



    override fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {

                CURRENT_ID = AUTH.currentUser?.uid.toString()
                REF_DATABASE = FirebaseDatabase.getInstance().reference
//                initUser()

                onSuccess() }
            .addOnFailureListener {
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)}
//            .child(AUTH.currentUser?.uid.toString())
    }

    override fun signOut() {
        AUTH.signOut()
        CURRENT_ID = ""
        ID_REPORT = ""
        USER_DATA = UserData()
//        Log.d("Value", "Exit" +" "+  USER_DATA.name.toString() +" "+  CURRENT_ID)
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