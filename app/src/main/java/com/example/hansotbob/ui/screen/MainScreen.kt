package com.example.hansotbob.ui.screen

import MealkitsDetailScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.component.common.HansotThemeNavigationBar
import com.example.hansotbob.component.common.ItemBar
import com.example.hansotbob.data.ListItem
import com.example.hansotbob.fragment.CategoryFragmentContainer
import com.example.hansotbob.ui.screen.detail.FoodShareDetailScreen
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
        ListItem.MealContent(R.drawable.food_image, "집밥1", "모집중", "시흥시 정왕동 산기대학로", "3000", true),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분2", "모집중", "시흥시 정왕동 산기대학로", "1200", true),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분3", "모집중", "시흥시 정왕동 산기대학로", "100", false),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분4", "모집중", "시흥시 정왕동 산기대학로", "1000", false),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분5", "모집중", "시흥시 정왕동 산기대학로", "1000", false)
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