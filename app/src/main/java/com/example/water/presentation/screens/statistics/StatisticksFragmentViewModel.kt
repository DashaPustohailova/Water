package com.example.water.presentation.screens.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.data.storage.firebase.AllReportLiveData

class StatisticksFragmentViewModel(application: Application): AndroidViewModel(application) {
    val allReport = AllReportLiveData()
}