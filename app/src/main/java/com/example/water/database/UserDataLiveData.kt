package com.example.water.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.water.models.Report
import com.example.water.models.UserData
import com.example.water.utilits.CURRENT_ID
import com.example.water.utilits.REF_DATABASE
import com.example.water.utilits.USER_DATA
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class UserDataLiveData: LiveData<UserData>() {
    private val mDatabaseReferenceWater = REF_DATABASE?.ref
        ?.child("users")?.child(CURRENT_ID)

    private val listener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.getValue(UserData::class.java) ?: UserData()
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    //срабатывает, когда LiveData активна. LiveData активна, когда viewModel активна
    //viewModel активна, когда активен фрагмент, который к ней обращается
    override fun onActive() {
        mDatabaseReferenceWater?.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        mDatabaseReferenceWater?.removeEventListener(listener)
        super.onInactive()
    }
//    REF_DATABASE?.ref
//    ?.child("users")?.child(CURRENT_ID)
//    ?.addListenerForSingleValueEvent(object : ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot) {
//            USER_DATA = snapshot.getValue(UserData::class.java)?: UserData()
//            Log.d("Value", CURRENT_ID + USER_DATA.name.toString())
//        }
//
//        override fun onCancelled(error: DatabaseError) {
//        }
//
//    })
}