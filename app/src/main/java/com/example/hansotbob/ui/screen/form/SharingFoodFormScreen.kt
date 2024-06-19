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
    val foodType by viewModel.foodType.collectAsState()
    val category by viewModel.category.collectAsState()
    val quantity by viewModel.quantity.collectAsState()
    val manufactureDate by viewModel.manufactureDate.collectAsState()
    val place by viewModel.place.collectAsState()
    val tradeMethod by viewModel.tradeMethod.collectAsState()
    val description by viewModel.description.collectAsState()

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
                onValueChange = { viewModel.onTitleChange(it) },
                label = { Text("제목 입력") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Text("등록 폼")
                Spacer(modifier = Modifier.height(8.dp))
                Text("음식 종류")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = foodType,
                    onValueChange = { viewModel.onFoodTypeChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("카테고리")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = category,
                    onValueChange = { viewModel.onCategoryChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("양")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { viewModel.onQuantityChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("제조 일자")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = manufactureDate,
                    onValueChange = { viewModel.onManufactureDateChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("거래 장소")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = place,
                    onValueChange = { viewModel.onPlaceChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("거래 방법")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = tradeMethod,
                    onValueChange = { viewModel.onTradeMethodChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label = { Text("한줄 설명") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Button(
                onClick = { /* Handle image upload */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text("사진 업로드")
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Button(
                onClick = {
                    viewModel.uploadItem()
                    navController.popBackStack()
                },
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
