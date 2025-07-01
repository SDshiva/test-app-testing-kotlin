package com.shibam.testAppTesting.app.features.login.domain.usecase

import com.shibam.testAppTesting.app.features.login.domain.repository.UserRepository

class RegisterUseCase (private val repository: UserRepository) {
    fun execute(username: String, password: String): Boolean {
        return repository.register(username, password)
    }
}