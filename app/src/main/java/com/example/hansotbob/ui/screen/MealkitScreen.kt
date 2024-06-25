package com.example.hansotbob.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.fragment.CategoryFragmentContainer
import com.example.hansotbob.viewmodel.screen.MealkitScreenViewModel

@Composable
fun MealkitScreen(navController: NavController, viewModel: MealkitScreenViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()
    viewModel.loadItems()

    Column(modifier = Modifier.fillMaxSize()) {
        CategoryFragmentContainer(navController, items)
    }
}