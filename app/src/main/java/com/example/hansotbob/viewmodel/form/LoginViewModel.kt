package com.example.hansotbob.viewmodel.form

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hansotbob.auth.AuthManager

class LoginViewModel(private val authManager: AuthManager) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun signIn(onSuccess: () -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "이메일과 비밀번호를 입력하세요."
            return
        }
        isLoading = true
        authManager.signInWithEmail(email, password,
            onSuccess = {
                isLoading = false
                onSuccess()
            },
            onFailure = { exception ->
                isLoading = false
                errorMessage = exception.message.toString()
                Log.d("errormsg", "exception : $errorMessage")
            }
        )
    }
}
