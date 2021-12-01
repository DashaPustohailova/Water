package com.example.water.database

import androidx.lifecycle.LiveData
import com.example.water.models.UserData
import com.example.water.utilits.CURRENT_ID
import com.example.water.utilits.REF_DATABASE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

interface DatabaseRepository {
//    val allNotes: LiveData<List<AppNote>>
//    suspend fun insert(note: AppNote, onSuccess:()->Unit)
//    suspend fun delete(note: AppNote, onSuccess:()->Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit){}

    fun signOut(){}

    fun initUser(){}
    fun registration(userData: UserData, onSuccess: () -> Unit){}
}