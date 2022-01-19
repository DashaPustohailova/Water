package com.example.water.domain.usecase

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.repository.DatabaseRepository

class GetLastReportUseCase(private val repository: DatabaseRepository) {
    fun execute() : LiveData<Report> {
        return repository.getLastReport()
    }
}