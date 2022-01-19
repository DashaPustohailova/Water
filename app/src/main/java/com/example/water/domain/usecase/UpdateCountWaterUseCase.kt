package com.example.water.domain.usecase

import com.example.water.domain.models.Report
import com.example.water.domain.repository.DatabaseRepository
import com.example.water.utilits.REPOSITORY

class UpdateCountWaterUseCase(private val repository: DatabaseRepository) {
    fun execute(report: Report) {
        repository.updateCountOfWater(report = report)
    }
}