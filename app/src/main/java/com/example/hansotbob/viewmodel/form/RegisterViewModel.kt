package com.example.hansotbob.viewmodel.form

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hansotbob.auth.AuthManager
import com.example.hansotbob.auth.FirebaseAuthManager
import com.example.hansotbob.exception.AuthException

class RegisterViewModel(private val authManager: AuthManager) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun register(onSuccess: () -> Unit) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage = AuthException.EmptyFieldsException().message.toString()
            return
        }
        if (password != confirmPassword) {
            errorMessage = AuthException.PasswordMismatchException().message.toString()
            return
        }
        isLoading = true
        FirebaseAuthManager.signUpWithEmail(email, password,
            onSuccess = {
                isLoading = false
                onSuccess()
                Log.d("errormsg", "Successfully registered with email: $email")
            },
            onFailure = { exception ->
                isLoading = false
                errorMessage = exception.message.toString()
                Log.d("errormsg", "Exception during sign-up: $exception")
            }
        )
    }
}
