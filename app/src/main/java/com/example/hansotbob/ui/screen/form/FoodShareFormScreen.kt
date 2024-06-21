package com.example.hansotbob.ui.screen.form

import android.app.DatePickerDialog
import android.util.Log
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hansotbob.R
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.viewmodel.form.MealkitFormViewModel
import com.example.hansotbob.component.common.form.DatePicker
import com.example.hansotbob.component.common.form.FormButtonSelector
import com.example.hansotbob.component.common.form.FormDropdownSelector
import com.example.hansotbob.component.common.form.FormOutlinedTextField
import com.example.hansotbob.component.common.form.ImagePickerScreen
import java.util.Calendar

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FoodShareFormScreen(navController: NavController, viewModel: FoodShareFormViewModel = viewModel()) {
//    var categoryExpanded by remember { mutableStateOf(false) }
//    var quantityExpanded by remember { mutableStateOf(false) }
//    var methodExpanded by remember { mutableStateOf(false) }
//
//    val category by viewModel.category.collectAsState()
//    val quantity by viewModel.quantity.collectAsState()
//    val productionDate by viewModel.productionDate.collectAsState()
//    val title by viewModel.title.collectAsState()
//    val place by viewModel.place.collectAsState()
//    val selectedMethod by viewModel.method.collectAsState()
//    val price by viewModel.price.collectAsState()
//    val description by viewModel.description.collectAsState()
//
//    val categoryOptions = listOf("한식", "분식", "중식", "일식", "양식")
//    val quantityOptions = listOf("1인분", "2인분", "3인분", "4인분", "5인분", "6인분 이상")
//    val methodOptions = listOf("직거래", "택배", "문고리")
//
//    Scaffold(
//        topBar = {
//            AppBar(
//                title = "밀키트 등록 화면",
//                navController = navController,
//                modifier = Modifier
//                    .background(Color.White)
//                    .border(BorderStroke(0.5.dp, Color.Gray), shape = RectangleShape)
//            )
//        }
//    ) { innerPadding ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(16.dp)
//        ) {
//            item {
//                // 제목 입력
//                FormOutlinedTextField(
//                    label = "제목",
//                    hint = "제목을 입력하세요",
//                    value = title,
//                    onValueChange = { viewModel.setTitle(it) },
//                    keyboardOptions = KeyboardOptions.Default,
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 등록폼 카테고리
//                FormDropdownSelector(
//                    label = "카테고리",
//                    hint = "카테고리 선택",
//                    options = categoryOptions,
//                    selectedOption = category,
//                    onOptionSelected = { selectedOption ->
//                        viewModel.setCategory(selectedOption)
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 양 선택
//                FormDropdownSelector(
//                    label = "양",
//                    hint = "양 선택",
//                    options = quantityOptions,
//                    selectedOption = quantity,
//                    onOptionSelected = { selectedOption ->
//                        viewModel.setQuantity(selectedOption)
//                    },
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 제조 일자
//                Text("제조일자", fontWeight = FontWeight.Bold, fontSize = 14.sp)
//                Spacer(modifier = Modifier.height(4.dp))
//                DatePicker(productionDate) { date ->
//                    viewModel.setProductionDate(date)
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 거래 장소
//                FormOutlinedTextField(
//                    label = "거래 장소",
//                    hint = "거래 장소를 입력하세요",
//                    value = place,
//                    onValueChange = { newValue ->
//                        viewModel.setPlace(newValue)
//                    },
//                    keyboardOptions = KeyboardOptions.Default,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 거래 방법
//                FormButtonSelector(
//                    label = "거래 방법",
//                    options = methodOptions,
//                    selectedOption = selectedMethod,
//                    onOptionSelected = { selectedOption ->
//                        viewModel.setMethod(selectedOption)
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 가격 입력
//                FormOutlinedTextField(
//                    label = "가격",
//                    hint = "가격 입력",
//                    value = price,
//                    onValueChange = { viewModel.setPrice(it) },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 한줄 설명
//                FormOutlinedTextField(
//                    label = "한줄 설명",
//                    hint = "간단한 설명을 입력하세요",
//                    value = description,
//                    onValueChange = { viewModel.setDescription(it) },
//                    keyboardOptions = KeyboardOptions.Default,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 사진 등록ㅅ
//                Text("사진 등록", fontWeight = FontWeight.Bold, fontSize = 14.sp)
//                Spacer(modifier = Modifier.height(4.dp))
//                ImagePickerScreen(modifier = Modifier.fillMaxWidth())
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // 등록 버튼
//                Button(
//                    onClick = {
//                        viewModel.uploadFoodShare()
//                        navController.popBackStack()
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(48.dp)
//                ) {
//                    Text("등록")
//                }
//            }
//        }
//    }
//}