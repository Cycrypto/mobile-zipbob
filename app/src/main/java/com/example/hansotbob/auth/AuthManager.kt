package com.example.hansotbob.auth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface AuthManager {
    fun signInWithEmail(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun signInWithGoogle(account: GoogleSignInAccount, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}
