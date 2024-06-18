package com.example.hansotbob.auth

import com.example.hansotbob.exception.AuthException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthManager(private val auth: FirebaseAuth) : AuthManager {
    override fun signInWithEmail(email: String, password: String, onSuccess: () -> Unit, onFailure: (AuthException) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    val exception = when (val e = task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> AuthException.WrongPasswordException()
                        is FirebaseAuthInvalidUserException -> AuthException.UserNotFoundException()
                        is FirebaseAuthException -> when (e.errorCode) {
                            "ERROR_USER_DISABLED" -> AuthException.UserDisabledException()
                            else -> AuthException.UnknownErrorException(e.message ?: "Authentication failed")
                        }
                        else -> AuthException.UnknownErrorException(e?.message ?: "Authentication failed")
                    }
                    onFailure(exception)
                }
            }
    }

    override fun signInWithGoogle(account: GoogleSignInAccount, onSuccess: () -> Unit, onFailure: (AuthException) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    val exception = AuthException.UnknownErrorException(task.exception?.message ?: "Google sign in failed")
                    onFailure(exception)
                }
            }
    }
}
