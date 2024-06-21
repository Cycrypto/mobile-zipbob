package com.example.hansotbob.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.MainActivity
import com.example.hansotbob.navigation.MyPageNavHost
import com.example.hansotbob.ui.screen.mypage.MyPageBuyDetailScreen
import com.example.hansotbob.ui.screen.mypage.MyPageOtherReviewScreen
import com.example.hansotbob.ui.screen.mypage.MyPageReviewScreen
import com.example.hansotbob.ui.screen.mypage.MyPageSaleDetailScreen
import com.example.hansotbob.ui.screen.mypage.MyPageScreen
import com.example.hansotbob.ui.theme.HansotbobTheme

class MyPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToMainScreen()
            }
        })
        setContent{
            HansotbobTheme {
                val navController = rememberNavController()
                MyPageNavHost(navController)
            }
        }
    }
    private fun navigateToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}

