package com.example.hansotbob.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hansotbob.ui.screen.mypage.MyPageBuyDetailScreen
import com.example.hansotbob.ui.screen.mypage.MyPageOtherReviewScreen
import com.example.hansotbob.ui.screen.mypage.MyPageReviewScreen
import com.example.hansotbob.ui.screen.mypage.MyPageSaleDetailScreen
import com.example.hansotbob.ui.screen.mypage.MyPageScreen

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