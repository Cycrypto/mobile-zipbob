package com.example.hansotbob.ui.screen.form

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.component.common.form.DatePicker
import com.example.hansotbob.viewmodel.form.MealkitFormViewModel
import androidx.compose.runtime.remember as remember1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealkitFormScreen(navController: NavController, viewModel: MealkitFormViewModel = viewModel()) {
    var categoryExpanded by remember1 { mutableStateOf(false) }
    var quantityExpanded by remember1 { mutableStateOf(false) }
    var methodExpanded by remember1 { mutableStateOf(false) }

    val category by viewModel.category.collectAsState()
    val quantity by viewModel.quantity.collectAsState()
    val productionDate by viewModel.productionDate.collectAsState()
    val title by viewModel.title.collectAsState()
    val place by viewModel.place.collectAsState()
    val selectedMethod by viewModel.method.collectAsState()
    val price by viewModel.price.collectAsState()
    val description by viewModel.description.collectAsState()

    val categoryOptions = listOf("한식", "분식", "중식", "일식", "양식")
    val quantityOptions = listOf("1인분", "2인분", "3인분", "4인분", "5인분", "6인분 이상")
    val methodOptions = listOf("직거래", "택배", "문고리")
    val errorMessage = remember1 { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppBar(
                title = "밀키트 등록 화면",
                navController = navController,
                modifier = Modifier
                    .background(Color.White)
                    .border(BorderStroke(0.5.dp, Color.Gray), shape = RectangleShape)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // 제목 입력
            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = { viewModel.setTitle(it) },
                    label = { Text("제목 입력") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // 등록폼 카테고리
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    Text("카테고리")
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .clickable { categoryExpanded = true }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = category.ifEmpty { "카테고리 선택" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        categoryOptions.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setCategory(option)
                                    categoryExpanded = false
                                },
                                text = { Text(option) }
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            // 양
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Text("양")
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .clickable { quantityExpanded = true }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = quantity.ifEmpty { "양 선택" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = quantityExpanded,
                        onDismissRequest = { quantityExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        quantityOptions.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setQuantity(option)
                                    quantityExpanded = false
                                },
                                text = { Text(option) }
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            // 제조 일자
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Text("제조 일자")
                    Spacer(modifier = Modifier.height(4.dp))
                    DatePicker(productionDate) { date ->
                        viewModel.setProductionDate(date)
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // 거래 장소
                    Text("거래 장소")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = place,
                        onValueChange = {
                            viewModel.setPlace(it)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // 거래 방법
                    Text("거래 방법")
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .clickable { methodExpanded = true }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = selectedMethod.ifEmpty { "거래 방법 선택" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = methodExpanded,
                        onDismissRequest = { methodExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        methodOptions.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.setMethod(option)
                                    methodExpanded = false
                                },
                                text = { Text(option) }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // 가격
                    Text("가격")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = price,
                        onValueChange = {
                            viewModel.setPrice(it)
                        },
                        label = { Text("가격 입력") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // 한줄 설명
            item {
                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        viewModel.setDescription(it)
                    },
                    label = { Text("한줄 설명") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }


            // 버튼 1
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

            // 버튼 2
            item {
                Button(
                    onClick = {
                        when {
                            !viewModel.register() -> {
                                errorMessage.value = "모든 빈칸을 입력하세요"
                            }
                            !viewModel.checkPriceIsValid() -> {
                                errorMessage.value = "가격은 숫자여야 합니다"
                            }
                            else -> {
                                viewModel.uploadMealkit()
                                navController.popBackStack()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("등록")
                }

                if (errorMessage.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp)) // 버튼과 오류 메시지 사이의 간격
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = errorMessage.value,
                            color = Color.Red,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // 오류 메시지와 아래 요소 사이의 간격
                }

            }
        }
    }
}


