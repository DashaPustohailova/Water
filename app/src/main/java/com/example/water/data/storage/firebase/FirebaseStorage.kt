package com.example.water.data.storage.firebase

import android.util.Log
import com.example.water.data.storage.webStorage
import com.example.water.domain.models.UserData
import com.example.water.utilits.AUTH
import com.example.water.utilits.CURRENT_ID
import com.example.water.utilits.REF_DATABASE
import com.example.water.utilits.USER_DATA
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class FirebaseStorage : webStorage {

    override fun init(){
        AUTH = FirebaseAuth.getInstance()
    }

    override fun initUser() {
        REF_DATABASE?.ref
            ?.child("users")?.child(CURRENT_ID)
            ?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    USER_DATA = snapshot.getValue(UserData::class.java)?: UserData()
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

}