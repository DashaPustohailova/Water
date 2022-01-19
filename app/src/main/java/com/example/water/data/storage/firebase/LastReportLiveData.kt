package com.example.water.data.storage.firebase

import androidx.lifecycle.LiveData
import com.example.water.data.utilits.*
import com.example.water.domain.models.Report
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class LastReportLiveData: LiveData<Report>(){
    private val mDatabaseReferenceWater = REF_DATABASE?.ref
    ?.child("report")?.child(CURRENT_ID)?.orderByKey()?.limitToLast(1)

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            var s = Report()
            for (data in snapshot.getChildren()) {
                s = data.getValue(Report::class.java)?: Report()
                ID_REPORT = data.key
            }
            value = s
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