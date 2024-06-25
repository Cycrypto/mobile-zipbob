package com.example.hansotbob.auth

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface GoogleSignInInterface {
    fun signIn()
    fun handleSignInResult(data: Intent?, onSuccess: (GoogleSignInAccount) -> Unit, onFailure: (String) -> Unit)
}