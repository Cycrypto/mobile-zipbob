package com.example.hansotbob.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.MainActivity
import com.example.hansotbob.auth.GoogleSignInManager
import com.example.hansotbob.auth.FirebaseAuthManager
import com.example.hansotbob.auth.AuthManager
import com.example.hansotbob.auth.GoogleSignInInterface
import com.example.hansotbob.exception.AuthException
import com.example.hansotbob.ui.screen.MainScreen
import com.example.hansotbob.ui.screen.form.LoginScreen
import com.example.hansotbob.ui.screen.form.RegisterScreen
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInManager: GoogleSignInInterface
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth and Managers
        auth = Firebase.auth
        googleSignInManager = GoogleSignInManager(this)
        authManager = FirebaseAuthManager(auth)

        setContent {
            HansotbobTheme {
                val navController = rememberNavController()
                AppNavHost(navController, authManager, googleSignInManager)
            }
        }
    }

    fun navigateToMainScreen(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        googleSignInManager.handleSignInResult(data,
            onSuccess = { account ->
                authManager.signInWithGoogle(account,
                    onSuccess = {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    },
                    onFailure = { exception:AuthException ->
                        Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            onFailure = { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
    }

    @Composable
    fun AppNavHost(navController: NavHostController, authManager: AuthManager, googleSignInManager: GoogleSignInInterface) {
        NavHost(navController, startDestination = "login") {
            composable("login") {
                LoginScreen(navController, authManager, googleSignInManager)
            }
            composable("register") {
                RegisterScreen(navController, authManager)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HansotbobTheme {
            val navController = rememberNavController()
            AppNavHost(navController, authManager, googleSignInManager)
        }
    }
}
