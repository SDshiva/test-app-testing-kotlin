package com.shibam.testAppTesting.login_app.domain.usecase

import com.shibam.testAppTesting.login_app.domain.repository.UserRepository

class LoginUseCase (private val repository: UserRepository) {
    fun execute(username: String, password: String): Boolean {
        return repository.login(username, password)
    }
}