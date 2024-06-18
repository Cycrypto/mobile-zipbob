package com.example.hansotbob.auth

import com.example.hansotbob.exception.AuthException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface AuthManager {
    fun signInWithEmail(email: String, password: String, onSuccess: () -> Unit, onFailure: (AuthException) -> Unit)
    fun signInWithGoogle(account: GoogleSignInAccount, onSuccess: () -> Unit, onFailure: (AuthException) -> Unit)
}
