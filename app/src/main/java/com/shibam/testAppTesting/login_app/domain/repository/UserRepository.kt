package com.shibam.testAppTesting.login_app.domain.repository

interface UserRepository {
     fun login(username: String, password: String): Boolean
     fun register(username: String, password: String): Boolean
}