package com.example.hansotbob.ui.screen.form

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hansotbob.R
import java.util.Calendar
import androidx.compose.runtime.remember as remember1
import com.example.hansotbob.viewmodel.form.MealkitFormViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.component.common.AppBar

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
                        viewModel.uploadMealkit()
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
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = title, color = Color.Black)
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back"
                )
            }
        },
        modifier = modifier.background(Color.White)
    )
}

@Composable
fun DatePicker(selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var dateText by remember1 { mutableStateOf(selectedDate) }

    ClickableText(
        text = AnnotatedString(dateText.ifEmpty { "날짜 선택" }),
        onClick = {
            Log.d("DatePicker", "Text clicked")
            DatePickerDialog(context, { _, y, m, d ->
                dateText = "$y-${m + 1}-$d"
                onDateSelected(dateText)
            }, year, month, day).show()
        },
        style = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),
        modifier = Modifier
            .fillMaxWidth()

            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(4.dp))
            .padding(16.dp)
    )
}


