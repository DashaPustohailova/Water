package com.example.water.domain.usecase

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.repository.DatabaseRepository

class CreateCurrentDataReportUseCase(private val repository: DatabaseRepository) {
    fun execute()  {
        return repository.createCurrentDataReport()
    }
}