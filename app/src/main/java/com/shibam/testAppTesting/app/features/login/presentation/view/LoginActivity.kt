package com.shibam.testAppTesting.app.features.login.presentation.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.shibam.testAppTesting.R
import com.shibam.testAppTesting.app.features.login.domain.repository.FakeUserRepository
import com.shibam.testAppTesting.app.features.login.domain.repository.UserRepository
import com.shibam.testAppTesting.app.features.login.domain.usecase.LoginUseCase
import com.shibam.testAppTesting.app.features.login.domain.usecase.RegisterUseCase
import com.shibam.testAppTesting.app.features.login.presentation.view_model.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        initViews()
        dependencySetup()
        observerEvents()
        btnActivity()
    }

    private fun initViews(){
        // Initialize UI components
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnRegister = findViewById(R.id.btn_register)
        tvResult = findViewById(R.id.tv_result)
    }

    private fun dependencySetup(){
        val repository: UserRepository = FakeUserRepository()
        val loginUseCase = LoginUseCase(repository)
        val registerUseCase = RegisterUseCase(repository)
        viewModel = LoginViewModel(loginUseCase, registerUseCase)
    }

    private fun observerEvents() {
        // Observe login result
        viewModel.loginResult.observe(this) { result ->
            tvResult.text = if (result) "Login Successful" else "Login Failed"
        }

        // Observe registration result
        viewModel.registerResult.observe(this) { result ->
            tvResult.text = if (result) "Registration Successful" else "Registration Failed"
        }
    }
    private fun btnActivity() {
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInput(username, password)) {
                viewModel.login(username, password)
            }
        }

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInput(username, password)) {
                viewModel.register(username, password)
            }
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        return when {
            username.isEmpty() -> {
                etUsername.error = "Username is required"
                etUsername.requestFocus()
                false
            }
            password.isEmpty() -> {
                etPassword.error = "Password is required"
                etPassword.requestFocus()
                false
            }
            password.length < 4 -> {
                etPassword.error = "Password must be at least 4 characters"
                etPassword.requestFocus()
                false
            }
            else -> true
        }
    }


}