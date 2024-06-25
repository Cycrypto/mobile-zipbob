package com.example.hansotbob.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hansotbob.fragment.CategoryFragmentContainer
import com.example.hansotbob.viewmodel.screen.HomeFoodScreenViewModel
import com.example.hansotbob.viewmodel.screen.MealkitScreenViewModel

@Composable
fun HomeFoodScreen(navController: NavController, viewModel: HomeFoodScreenViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()
    Log.d("HomeFoodScreen2", "item : $items")
    viewModel.loadItems()

    Column(modifier = Modifier.fillMaxSize()) {
        CategoryFragmentContainer(navController, items)
    }
}