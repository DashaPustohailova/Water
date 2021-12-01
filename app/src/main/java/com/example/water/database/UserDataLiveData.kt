package com.example.water.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.water.models.UserData
import com.example.water.utilits.AUTH
import com.example.water.utilits.REF_DATABASE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class UserDataLiveData: LiveData<List<UserData>>(){
    private val mDatabaseReferenceUserData = REF_DATABASE?.ref
        ?.child("users")

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                //если не смогли получить запись, то просто возвращаем пустую запись
                it.getValue(UserData::class.java)?: UserData()
            }
        }


        override fun onCancelled(error: DatabaseError) {
        }

    }

    //срабатывает, когда LiveData активна. LiveData активна, когда viewModel активна
    //viewModel активна, когда активен фрагмент, который к ней обращается
    override fun onActive() {
        mDatabaseReferenceUserData?.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        mDatabaseReferenceUserData?.removeEventListener(listener)
        super.onInactive()
    }
}