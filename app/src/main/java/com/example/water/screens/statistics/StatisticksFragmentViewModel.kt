package com.example.water.screens.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.water.database.AllReportLiveData

class StatisticksFragmentViewModel(application: Application): AndroidViewModel(application) {
    val allReport = AllReportLiveData()
}