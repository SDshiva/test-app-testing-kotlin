package com.shibam.testAppTesting.login_app.domain.repository

class FakeUserRepository : UserRepository {

    private val users = mutableMapOf<String, String>() // username to password mapping

    override fun login(username: String, password: String) = users[username] == password


    override fun register(username: String, password: String): Boolean {
        if (users.containsKey(username)) {
            return false // User already exists
        }
        users[username] = password
        return true // Registration successful
    }
}