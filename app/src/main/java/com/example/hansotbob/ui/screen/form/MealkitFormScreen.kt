package com.example.hansotbob.ui.screen.form

import android.app.DatePickerDialog
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme
import java.util.Calendar
import androidx.compose.runtime.remember as remember1

private var Nothing?.value: TextFieldValue
    get() {
        TODO("Not yet implemented")
    }
    set(value) {}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealkitFormScreen(navController: NavController) {
    var expanded by remember1 { mutableStateOf(false) }
    var selectedCategory by remember1 { mutableStateOf("") }
    val textFieldValue = remember1 { mutableStateOf(TextFieldValue(selectedCategory.ifEmpty { "카테고리 선택" })) }
    var title by remember1 { mutableStateOf(TextFieldValue("")) }
    var foodType by remember1 { mutableStateOf(TextFieldValue("")) }
    var selectedQuantity by remember1 { mutableStateOf("") }
    var productionDate by remember1 { mutableStateOf("") }
    var place by remember1 { mutableStateOf(TextFieldValue("")) }
    var selectedMethod by remember1 { mutableStateOf("") }
    var price by remember1 { mutableStateOf(TextFieldValue("")) }
    var description by remember1 { mutableStateOf(TextFieldValue("")) }

    val categoryOptions = listOf("한식", "분식")//, "중식", "일식", "양식")
    val quantityOptions = listOf("1인분", "2인분")//, "3인분", "4인분", "5인분", "6인분 이상")
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
            //제목입력
            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
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
                        .padding(16.dp)
                ) {
                    Text("등록 폼")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("카테고리")
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .clickable { expanded = true }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = selectedCategory.ifEmpty { "카테고리 선택" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        categoryOptions.forEach { category ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedCategory = category
                                    val textFieldValue = null
                                    textFieldValue.value = TextFieldValue(category)
                                    expanded = false
                                },
                                text = { Text(category) }
                            )
                        }
                    }
                }
            }//여기서 한번 자르기
            // 양
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {
                    Text("양")
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .clickable { expanded = true }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = selectedCategory.ifEmpty { "양 선택" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        quantityOptions.forEach { category ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedCategory = category
                                    val textFieldValue = null
                                    textFieldValue.value = TextFieldValue(category)
                                    expanded = false
                                },
                                text = { Text(category) }
                            )
                        }
                    }
                }
            }//여기서 한번 자르기
            //기타 등등
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ){
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("제조 일자")
                    Spacer(modifier = Modifier.height(4.dp))
                    DatePicker(productionDate) { date -> productionDate = date }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("거래 장소")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = place,
                        onValueChange = { place = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("거래 방법")
                    Spacer(modifier = Modifier.height(4.dp))
                    SelectionField(
                        selectedOption = selectedMethod,
                        options = methodOptions,
                        label = "거래 방법 선택",
                        onItemSelected = { selectedMethod = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("가격")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        label = { Text("가격 입력") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }



            item { Spacer(modifier = Modifier.height(16.dp)) }
            //한줄 설명
            item {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("한줄 설명") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
            //버튼 1
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
            //버튼 2
            item {
                Button(
                    onClick = { /* Handle registration */ },
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
fun SelectionField(
    selectedOption: String,
    options: List<String>,
    label: String,
    onItemSelected: (String) -> Unit
) {
    var showDialog by remember1 { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDialog = true },
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                Icon(Icons.Default.MoreHoriz, contentDescription = null)
            }
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = label) },
            text = {
                Column {
                    options.forEach { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemSelected(option)
                                    showDialog = false
                                }
                                .padding(8.dp)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("닫기")
                }
            }
        )
    }
}

@Composable
fun DatePicker(selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var dateText by remember1 { mutableStateOf(selectedDate) }

    OutlinedTextField(
        value = dateText,
        onValueChange = {},
        label = { Text("날짜 선택") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                DatePickerDialog(context, { _, y, m, d ->
                    dateText = "$y-${m + 1}-$d"
                    onDateSelected(dateText)
                }, year, month, day).show()
            },
        readOnly = true,
        trailingIcon = {
            Icon(Icons.Default.CalendarToday, contentDescription = null)
        }
    )
}

@Preview
@Composable
private fun PreviewMealkitFormScreen() {
    HansotbobTheme {
        val navController = rememberNavController()
        MealkitFormScreen(navController)
    }
}
