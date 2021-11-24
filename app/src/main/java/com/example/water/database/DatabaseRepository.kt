package com.example.water.database

import com.example.water.models.UserData

interface DatabaseRepository {
//    val allNotes: LiveData<List<AppNote>>
//    suspend fun insert(note: AppNote, onSuccess:()->Unit)
//    suspend fun delete(note: AppNote, onSuccess:()->Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit){}

    fun signOut(){}

    fun registration(userData: UserData, onSuccess: () -> Unit){}
}