package com.example.water.presentation.screens.start

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.water.utilits.APP_ACTIVITY
import com.example.water.utilits.REPOSITORY

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val context = application

    fun initDatabase(onSuccess: () -> Unit) {
        REPOSITORY.connectToDatabase({
            onSuccess() },
            { Toast.makeText(APP_ACTIVITY, "Проблемы при авторизации", Toast.LENGTH_SHORT).show() })
    }


}
