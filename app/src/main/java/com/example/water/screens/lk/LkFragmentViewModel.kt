package com.example.water.screens.lk

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.water.database.UserDataLiveData
import com.example.water.models.UserData
import com.example.water.utilits.AUTH
import com.example.water.utilits.CURRENT_ID
import com.example.water.utilits.REF_DATABASE
import com.example.water.utilits.REPOSITORY
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LkFragmentViewModel(application: Application): AndroidViewModel(application) {
    val userData: LiveData<List<UserData>> = REPOSITORY.userData

}