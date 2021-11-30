package com.example.water.screens.lk

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LkFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val mAuth = FirebaseAuth.getInstance()

    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())

//    val nameUser = mDatabaseReference.ref?.child("userData").once("value)


}