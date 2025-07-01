package com.shibam.testAppTesting.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shibam.testAppTesting.app.features.login.domain.repository.FakeUserRepository
import com.shibam.testAppTesting.app.features.login.domain.usecase.LoginUseCase
import com.shibam.testAppTesting.app.features.login.domain.usecase.RegisterUseCase
import com.shibam.testAppTesting.app.features.login.presentation.view_model.LoginViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        val repository = FakeUserRepository()
        val loginUseCase = LoginUseCase(repository)
        val registerUseCase = RegisterUseCase(repository)
        viewModel = LoginViewModel(loginUseCase, registerUseCase)
    }

    @Test
    fun `register then login returns true`() {
        viewModel.register("shibam", "1234")
        viewModel.registerResult.value?.let { assertEquals(true, it) }

        viewModel.login("shibam", "1234")
        viewModel.loginResult.value?.let { assertEquals(true, it) }
    }

    @Test
    fun `register with existing username returns false`() {
        viewModel.register("user", "1234")
        viewModel.register("user", "4567")
        viewModel.registerResult.value?.let { assertEquals(false, it) }
    }
}