package com.example.water.database

import androidx.lifecycle.LiveData
import com.example.water.models.Report
import com.google.firebase.auth.FirebaseAuth

class AllReportsLiveData: LiveData<List<Report>>() {
    private val mAuth = FirebaseAuth.getInstance()
}