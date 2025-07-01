package com.shibam.testAppTesting.domain

import com.shibam.testAppTesting.app.features.login.domain.repository.FakeUserRepository
import com.shibam.testAppTesting.app.features.login.domain.repository.UserRepository
import com.shibam.testAppTesting.app.features.login.domain.usecase.LoginUseCase
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {
    private lateinit var repository: UserRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        repository = FakeUserRepository()
        loginUseCase = LoginUseCase(repository)
    }

    @Test
    fun `login with correct credentials returns true`() {
        repository.register("shibam", "1234")
        assertTrue(loginUseCase.execute("shibam", "1234"))
    }

    @Test
    fun `login with incorrect password returns false`() {
        repository.register("shibam", "1234")
        assertFalse(loginUseCase.execute("shibam", "wrong"))
    }
}