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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hansotbob.MainActivity
import com.example.hansotbob.R
import com.example.hansotbob.adapter.TransactionAdapter
import com.example.hansotbob.dto.Transaction
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

@Composable
fun MyPageNavHost(navController: NavHostController){
    NavHost(navController, startDestination = "mypage"){
        composable("mypage"){
            MyPageScreen(navController = navController)
        }
        composable("buy"){
            MyPageBuyDetailScreen(navController = navController)
        }
        composable("sales"){
            MyPageSaleDetailScreen(navController = navController)
        }
        composable("myReviews"){
            MyPageReviewScreen(navController = navController)
        }
        composable("receivedReviews"){
            MyPageOtherReviewScreen(navController = navController)
        }

    }
}