package com.example.water.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.water.models.Report
import com.example.water.utilits.CURRENT_ID
import com.example.water.utilits.REF_DATABASE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import android.R.attr.name
import com.example.water.utilits.ID_REPORT


class LastReportLiveData: LiveData<Report>(){
    private val mDatabaseReferenceWater = REF_DATABASE?.ref
    ?.child("report")?.child(CURRENT_ID)?.orderByKey()?.limitToLast(1)

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            var s: Report = Report()
            for (data in snapshot.getChildren()) {
                s = data.getValue(Report::class.java)?: Report()
                ID_REPORT = data.key
                Log.d("Value"," currentDate  ${ID_REPORT}")
            }
            value = s
//            Log.d("Value"," currentDate  ${value}")
//            ID_REPORT = snapshot.key
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