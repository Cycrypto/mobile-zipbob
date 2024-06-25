package com.example.hansotbob.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.CommunityCardWithBadge
import com.example.hansotbob.R
import com.example.hansotbob.fragment.CategoryFragmentContainer
import com.example.hansotbob.viewmodel.screen.IngredientShreViewModel

@Composable
fun IngredientScreen(navController: NavController, viewModel: IngredientShreViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()
    viewModel.loadItems()
    Log.d("Ingredient", "start category : ${items}")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            CategoryFragmentContainer(navController, items)
        }
        Button(
            onClick = {
                navController.navigate("joined_parties")
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(width = 100.dp, height = 40.dp) // 적절한 크기로 설정
        ) {
            Text(text = "참가목록", color = Color.White)
        }
    }

    Log.d("Ingredient", "end category")
}