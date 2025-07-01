package com.shibam.testAppTesting.app.features.login.domain.repository

interface UserRepository {
     fun login(username: String, password: String): Boolean
     fun register(username: String, password: String): Boolean
}