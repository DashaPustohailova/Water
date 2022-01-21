package com.example.water.domain.usecase

import com.example.water.domain.models.Report
import com.example.water.domain.repository.DatabaseRepository

class UpdateCountWaterUseCase(private val repository: DatabaseRepository) {
    fun execute(report: Report) {
        repository.updateCountOfWater(report = report)
    }
}