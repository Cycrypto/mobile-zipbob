package com.example.hansotbob.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.fragment.CategoryFragmentContainer
import com.example.hansotbob.viewmodel.screen.IngredientShreViewModel

@Composable
fun IngredientScreen(navController: NavController, viewModel: IngredientShreViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()
    viewModel.loadItems()
    Log.d("Ingredient", "start category : ${items}")
    Column(modifier = Modifier.fillMaxSize()) {
        CategoryFragmentContainer(navController, items)
    }
    Log.d("Ingredient", "end category")
}