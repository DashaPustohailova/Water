package com.example.water.database

import androidx.lifecycle.LiveData
import com.example.water.models.Report
import com.example.water.utilits.REF_DATABASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class AllReportLiveData : LiveData<List<Report>> (){
    // папка с данными о количестве выпитой воды
    private val mDatabaseReferenceWater = REF_DATABASE?.ref?.child("waterData")

    private val listener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                //если не смогли получить запись, то просто возвращаем пустую запись
                it.getValue(Report::class.java)?:Report()
            }
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
}

