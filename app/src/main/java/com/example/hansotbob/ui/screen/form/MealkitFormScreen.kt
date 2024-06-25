package com.example.hansotbob.ui.screen.form

import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.compose.runtime.remember as remember1
import com.example.hansotbob.viewmodel.form.MealkitFormViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.component.common.form.DatePicker
import com.example.hansotbob.component.common.form.FormButtonSelector
import com.example.hansotbob.component.common.form.FormDropdownSelector
import com.example.hansotbob.component.common.form.FormOutlinedTextField
import com.example.hansotbob.component.common.form.ImagePickerScreen

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
            item {
                // 제목 입력
                FormOutlinedTextField(
                    label = "제목",
                    hint = "제목을 입력하세요",
                    value = title,
                    onValueChange = { viewModel.setTitle(it) },
                    keyboardOptions = KeyboardOptions.Default,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 등록폼 카테고리
                FormDropdownSelector(
                    label = "카테고리",
                    hint = "카테고리 선택",
                    options = categoryOptions,
                    selectedOption = category,
                    onOptionSelected = { selectedOption ->
                        viewModel.setCategory(selectedOption)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 양 선택
                FormDropdownSelector(
                    label = "양",
                    hint = "양 선택",
                    options = quantityOptions,
                    selectedOption = quantity,
                    onOptionSelected = { selectedOption ->
                        viewModel.setQuantity(selectedOption)
                    },
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제조 일자
                Text("제조일자", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                DatePicker(productionDate) { date ->
                    viewModel.setProductionDate(date)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 거래 장소
                FormOutlinedTextField(
                    label = "거래 장소",
                    hint = "거래 장소를 입력하세요",
                    value = place,
                    onValueChange = { newValue ->
                        viewModel.setPlace(newValue)
                    },
                    keyboardOptions = KeyboardOptions.Default,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 거래 방법
                FormButtonSelector(
                    label = "거래 방법",
                    options = methodOptions,
                    selectedOption = selectedMethod,
                    onOptionSelected = { selectedOption ->
                        viewModel.setMethod(selectedOption)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 가격 입력
                FormOutlinedTextField(
                    label = "가격",
                    hint = "가격 입력",
                    value = price.toString(),
                    onValueChange = { viewModel.setPrice(it.toInt()) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 한줄 설명
                FormOutlinedTextField(
                    label = "한줄 설명",
                    hint = "간단한 설명을 입력하세요",
                    value = description,
                    onValueChange = { viewModel.setDescription(it) },
                    keyboardOptions = KeyboardOptions.Default,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 사진 등록ㅅ
                Text("사진 등록", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                ImagePickerScreen(modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(16.dp))

                // 등록 버튼
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