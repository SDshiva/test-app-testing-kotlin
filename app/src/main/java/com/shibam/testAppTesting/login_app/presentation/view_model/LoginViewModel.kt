package com.shibam.testAppTesting.login_app.presentation.view_model


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shibam.testAppTesting.login_app.domain.usecase.LoginUseCase
import com.shibam.testAppTesting.login_app.domain.usecase.RegisterUseCase

class LoginViewModel (
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel(){
    val loginResult = MutableLiveData<Boolean>()
    val registerResult = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        loginResult.value = loginUseCase.execute(username, password)
    }

    fun register(username: String, password: String) {
        registerResult.value = registerUseCase.execute(username, password)
    }
}