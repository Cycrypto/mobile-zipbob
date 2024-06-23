package com.example.hansotbob.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hansotbob.auth.AuthManager
import com.example.hansotbob.auth.GoogleSignInInterface
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.ui.screen.form.LoginScreen
import com.example.hansotbob.ui.screen.form.RegisterScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    authManager: AuthManager,
    googleSignInManager: GoogleSignInInterface
) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, authManager, googleSignInManager)
        }
        composable("register") {
            RegisterScreen(navController, authManager)
        }
    }
}