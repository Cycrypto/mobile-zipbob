package com.example.hansotbob.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hansotbob.component.common.ItemBar
import com.example.hansotbob.ui.screen.HomeFoodScreen
import com.example.hansotbob.ui.screen.MealkitScreen
import com.example.hansotbob.ui.screen.OverviewScreen
import com.example.hansotbob.ui.screen.detail.FoodShareDetailScreen
import com.example.hansotbob.ui.screen.detail.MealkitsDetailScreen
import com.example.hansotbob.ui.screen.detail.OverviewDetailScreen
import com.example.hansotbob.ui.screen.form.SharingFoodFormScreen
import com.example.hansotbob.viewmodel.ListViewModel
import com.example.hansotbob.viewmodel.screen.HomeFoodScreenViewModel
import com.example.hansotbob.viewmodel.screen.OverviewScreenViewModel
import com.example.hansotbob.viewmodel.screen.detail.FoodShareDetailViewModel

@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "overview") {
        composable("overview"){
            val viewModel: ListViewModel = viewModel()
            Column(modifier = modifier.fillMaxSize()) {
                OverviewScreen(navController = navController, viewModel)
            }
        }

        composable("food_share") {
            Column(modifier = modifier.fillMaxSize()) {
                ItemBar()
                HomeFoodScreen(navController)
            }
        }

        composable("mealkit_share") {
            Column(modifier = modifier.fillMaxSize()) {
                ItemBar()
                MealkitScreen(navController)
            }
        }

        composable("foodshare_form"){
            SharingFoodFormScreen(navController)
        }

        composable("foodshare/detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: return@composable
            Log.d("NavGraph", "foodshare/detail/${itemId}")
            val viewModel: HomeFoodScreenViewModel = viewModel()
            FoodShareDetailScreen(navController, itemId, viewModel)
        }

        composable("mealkit/detail/{title}/{recruit}/{place}/{price}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val recruit = backStackEntry.arguments?.getString("recruit") ?: ""
            val place = backStackEntry.arguments?.getString("place") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            MealkitsDetailScreen(title = title, recruit = recruit, place = place, price = price)
        }

        composable("detail/{name}/{category}/{imageRes}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val category = backStackEntry.arguments?.getString("category") ?: ""
            val imageRes = backStackEntry.arguments?.getString("imageRes") ?: ""
            // Placeholder for the detailed screen based on name, category, and image resource
            OverviewDetailScreen(name, category, imageRes)
        }
    }
}


