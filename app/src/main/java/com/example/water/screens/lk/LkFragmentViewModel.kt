package com.example.water.screens.lk

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.database.UserDataLiveData
import com.example.water.utilits.AUTH
import com.example.water.utilits.REF_DATABASE
import com.example.water.utilits.REPOSITORY
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LkFragmentViewModel(application: Application): AndroidViewModel(application) {
    val userData = REPOSITORY.userData
}