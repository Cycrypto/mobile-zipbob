package com.example.hansotbob.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.example.hansotbob.ComposableFunction
import com.example.hansotbob.R
import com.example.hansotbob.component.common.HansotThemeNavigationBar
import com.example.hansotbob.data.ListItem
import com.example.hansotbob.fragment.CategoryFragmentContainer
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hansotbob.ui.screen.detail.FoodShareDetailScreen

import com.example.hansotbob.component.common.*
import com.example.hansotbob.ui.screen.detail.MealkitsDetailScreen
import com.example.hansotbob.ui.screen.detail.OverviewDetailScreen
import com.example.hansotbob.ui.screen.form.SharingFoodFormScreen
import com.example.hansotbob.ui.theme.HansotbobTheme

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "overview") {
        composable("overview"){
            Column(modifier = modifier.fillMaxSize()) {
                OverviewScreen(navController = navController, createDummyData2())
            }
        }

        composable("food_share") {
            Column(modifier = modifier.fillMaxSize()) {
                ItemBar()
                CategoryFragmentContainer(navController, createDummyData())
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

        composable("foodshare/detail/{title}/{recruit}/{place}/{price}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val recruit = backStackEntry.arguments?.getString("recruit") ?: ""
            val place = backStackEntry.arguments?.getString("place") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            FoodShareDetailScreen(title, recruit, place, price)
        }

        composable("mealkit/detail/{title}/{recruit}/{place}/{price}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val recruit = backStackEntry.arguments?.getString("recruit") ?: ""
            val place = backStackEntry.arguments?.getString("place") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            MealkitsDetailScreen(
                title = title,
                recruit = recruit,
                place = place,
                price = price,
                onBackClick = { navController.popBackStack() },
                onContactSellerClick = { /* Handle contact seller click */ },
                onBuyClick = { /* Handle buy click */ }
            )
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

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = getCurrentRoute(navController)

    Scaffold(
        content = { paddingValues ->
            NavGraph(navController = navController, modifier = Modifier.padding(paddingValues))
        },
        bottomBar = {
            if (currentRoute !in bottombarLocaiton()) {
                HansotThemeNavigationBar(navController)
            }
        }
    )
}


@Composable
fun getCurrentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

private fun createDummyData(): List<ListItem>{
    return listOf(
        ListItem.MealContent(R.drawable.food_image, "집밥1", "모집중", "시흥시 정왕동 산기대학로", "3000", true, 1),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분2", "모집중", "시흥시 정왕동 산기대학로", "1200", true, 0),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분3", "모집중", "시흥시 정왕동 산기대학로", "100", false, 1),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분4", "모집중", "시흥시 정왕동 산기대학로", "1000", false, 2),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분5", "모집중", "시흥시 정왕동 산기대학로", "1000", false, 1)
    )
}

private fun createDummyData2(): List<ListItem.Overview> {
    return listOf(
        ListItem.Overview("밥 가져가실분", "한식", R.drawable.community_image),
        ListItem.Overview("밥 가져가실분", "한식", R.drawable.community_image),
        ListItem.Overview("밥 가져가실분", "한식", R.drawable.community_image),
        ListItem.Overview("밥 가져가실분", "한식", R.drawable.community_image),
        ListItem.Overview("밥 가져가실분", "한식", R.drawable.community_image),
        ListItem.Overview("밥 가져가실분", "한식", R.drawable.community_image)
    )
}

private fun bottombarLocaiton(): List<String> {
    return listOf(
        "detail/{title}/{recruit}/{place}/{price}",
        "foodshare_form"
    )
}
@Preview
@Composable
private fun PreviewMainScreen(){
    HansotbobTheme {
        MainScreen()
    }
}