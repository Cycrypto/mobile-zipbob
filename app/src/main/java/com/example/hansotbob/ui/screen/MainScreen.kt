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
import com.example.hansotbob.ui.screen.detail.FoodShareDetailScreen


@Composable
fun NavGraph(startDestination: String = "category") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("category") {
            val items = createDummyData()
            CategoryFragmentContainer(navController, items)
        }
        composable("detail/{title}/{recruit}/{place}/{price}") {backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val recruit = backStackEntry.arguments?.getString("recruit") ?: ""
            val place = backStackEntry.arguments?.getString("place") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            FoodShareDetailScreen(title, recruit, place, price)
        }
    }
}

@Composable
fun MainScreen() {
    var loadView by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ItemBar()
                CategoryFragmentContainer(navController, createDummyData())
                if (loadView) {
                    ComposableFunction()
                }
            }
        },
        bottomBar = {
            HansotThemeNavigationBar()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "한솥밥",
                style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary)
            )
            Text(
                text = "집밥공유 플랫폼",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* TODO: Handle notifications */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search for plants...") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )

        IconButton(onClick = { /* TODO: Handle more options */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null
            )
        }
    }
}


@Composable
fun ComposableFunction() {
    Text("appbar content")
}

private fun createDummyData(): List<ListItem>{
    return listOf(
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        ListItem.MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000")
    )
}