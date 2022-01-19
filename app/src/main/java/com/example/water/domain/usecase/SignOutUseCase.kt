package com.example.water.domain.usecase

import com.example.water.domain.repository.DatabaseRepository


class SignOutUseCase(private val repository: DatabaseRepository) {
    fun execute() {
        repository.signOut()
    }
}