package com.example.water.domain.usecase

import androidx.lifecycle.LiveData
import com.example.water.domain.models.Report
import com.example.water.domain.repository.DatabaseRepository

class GetAllReportUseCase(private val repository: DatabaseRepository) {
    fun execute() : LiveData<List<Report>> {
        return repository.getAllReport()
    }
}