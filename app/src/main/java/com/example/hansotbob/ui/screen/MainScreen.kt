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
import com.example.hansotbob.ui.theme.HansotbobTheme

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            Column(modifier = modifier.fillMaxSize()) {
                ItemBar()
                CategoryFragmentContainer(navController, createDummyData())
            }
        }
        composable("detail/{title}/{recruit}/{place}/{price}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val recruit = backStackEntry.arguments?.getString("recruit") ?: ""
            val place = backStackEntry.arguments?.getString("place") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            FoodShareDetailScreen(title, recruit, place, price)
        }
        composable("overview"){

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
            if (currentRoute != "detail/{title}/{recruit}/{place}/{price}") {
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

@Preview
@Composable
private fun PreviewMainScreen(){
    HansotbobTheme {
        MainScreen()
    }
}