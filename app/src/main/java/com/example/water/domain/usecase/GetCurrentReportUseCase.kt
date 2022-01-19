package com.example.water.domain.usecase

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.repository.DatabaseRepository

class GetCurrentReportUseCase (private val repository: DatabaseRepository) {
    fun execute() : LiveData<Long> {
        return repository.getCurrentDateReport()
    }
}