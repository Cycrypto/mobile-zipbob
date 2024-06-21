package com.example.hansotbob.ui.screen.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.example.hansotbob.viewmodel.form.SharingFoodFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharingFoodFormScreen(navController: NavController, viewModel: SharingFoodFormViewModel = viewModel()) {
    val title by viewModel.title.collectAsState()
    val foodType by viewModel.category.collectAsState()
    val quantity by viewModel.quantity.collectAsState()
    val manufactureDate by viewModel.productionDate.collectAsState()
    val place by viewModel.place.collectAsState()
    val tradeMethod by viewModel.method.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        item {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.primary),
                title = { Text("등록 화면") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                    }
                }
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = title,
                onValueChange = { viewModel.setTitle(it) },
                label = { Text("집밥 가져가실분") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = foodType,
                onValueChange = { viewModel.setCategory(it) },
                label = { Text("1명") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = place,
                onValueChange = { viewModel.setPlace(it) },
                label = { Text("우리 집") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = quantity,
                onValueChange = { viewModel.setPrice(it) },
                label = { Text("1234원") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = manufactureDate,
                onValueChange = { viewModel.setProductionDate(it) },
                label = { Text("제조 일자") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = tradeMethod,
                onValueChange = { viewModel.setMethod(it) },
                label = { Text("거래 방법") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Button(
                onClick = { viewModel.uploadItem() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("등록")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSharingFoodFormScreen() {
    HansotbobTheme {
        val navController = rememberNavController()
        SharingFoodFormScreen(navController)
    }
}
