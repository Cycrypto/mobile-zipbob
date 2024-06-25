package com.example.hansotbob.viewmodel.form

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hansotbob.auth.AuthManager
import com.example.hansotbob.data.User
import com.example.hansotbob.exception.AuthException
import com.example.hansotbob.service.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginViewModel(private val authManager: AuthManager) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun signIn(onSuccess: () -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = throw AuthException.EmptyFormException()
            return
        }
        isLoading = true
        authManager.signInWithEmail(email, password,
            onSuccess = {
                val firebaseService = FirebaseService()
                firebaseService.initializeUserData(onSuccess = {
                    isLoading = false
                    onSuccess()
                }, onFailure = {error ->
                    isLoading = false
                    errorMessage = error
                    Log.d("errormsg", "exception : $errorMessage")
                })
            },
            onFailure = { exception ->
                isLoading = false
                errorMessage = exception.message ?: "Login failed"
            }
        )
    }
}
