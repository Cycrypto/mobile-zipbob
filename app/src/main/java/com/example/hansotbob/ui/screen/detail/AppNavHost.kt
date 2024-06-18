package com.example.hansotbob.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.ui.screen.detail.*

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "mypage",
        modifier = modifier
    ) {
        composable("mypage") { MyPageScreen(navController = navController) }
        composable("buy") { MyPageBuyDetailScreen() }
        composable("sales") { MyPageSaleDetailScreen() }
        composable("myReviews") { MyPageReviewScreen() }
        composable("receivedReviews") { MyPageOtherReviewScreen() }
        composable("profileEdit") { ProfileEditScreen() }
        composable("paymentDetail") { PaymentDetailScreen(navController = navController) }
        composable("paymentDetail2") { PaymentDetailScreen2() }
        composable("saleDetail") { SaleDetailScreen(navController = navController) }
        composable("saleDetail2") { SaleDetailScreen2() }
    }
}
