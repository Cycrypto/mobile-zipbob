package com.example.hansotbob.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.ui.screen.mypage.EditProfileScreen
import com.example.hansotbob.ui.screen.mypage.MyPageBuyDetailScreen
import com.example.hansotbob.ui.screen.mypage.MyPageOtherReviewScreen
import com.example.hansotbob.ui.screen.mypage.MyPageReviewScreen
import com.example.hansotbob.ui.screen.mypage.MyPageSaleDetailScreen
import com.example.hansotbob.ui.screen.mypage.MyPageScreen
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.user.UserProfileViewModel

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
        composable("profileEdit"){
            val viewModel:UserProfileViewModel = viewModel(factory = ViewModelFactory(FirebaseService()))
            EditProfileScreen(navController = navController, viewModel = viewModel)
        }

    }
}