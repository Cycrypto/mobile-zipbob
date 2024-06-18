package com.example.hansotbob.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hansotbob.auth.AuthManager

class RegisterViewModel(private val authManager: AuthManager) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun register(onSuccess: () -> Unit) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage = "모든 필드를 입력하세요."
            return
        }
        if (password != confirmPassword) {
            errorMessage = "비밀번호가 일치하지 않습니다."
            return
        }
        isLoading = true
        authManager.signInWithEmail(email, password,
            onSuccess = {
                isLoading = false
                onSuccess()
            },
            onFailure = { message ->
                isLoading = false
                errorMessage = message
            }
        )
    }
}
