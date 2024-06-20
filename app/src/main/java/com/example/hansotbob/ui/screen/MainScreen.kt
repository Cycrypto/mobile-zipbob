package com.example.hansotbob.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.component.common.HansotThemeNavigationBar
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.navigation.MainNavGraph
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.example.hansotbob.viewmodel.screen.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {
    val navController = rememberNavController()
    val currentRoute by viewModel.currentRoute.collectAsState()

    Scaffold(
        content = { paddingValues ->
            MainNavGraph(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        },
        bottomBar = {
            if (currentRoute !in bottombarLocaiton()) {
                HansotThemeNavigationBar(navController)
            }
        }
    )

    navController.addOnDestinationChangedListener { _, destination, _ ->
        viewModel.updateCurrentRoute(destination.route ?: "")
    }
}

private fun bottombarLocaiton(): List<String> {
    return listOf(
        "detail/{title}/{recruit}/{place}/{price}",
        "foodshare_form",
        "mealkit_form",
        "foodshare/detail/{title}/{recruit}/{place}/{price}",
        "mealkit/detail/{title}/{place}/{price}/{foodType}/{category}/{quantity}/{productionDate}/{exchangeMethod}/{description}/{state}"
    )
}

@Preview
@Composable
private fun PreviewMainScreen() {
    HansotbobTheme {
        MainScreen()
    }
}
