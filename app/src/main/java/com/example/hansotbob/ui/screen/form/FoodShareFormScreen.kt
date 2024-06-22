import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.component.common.form.DatePicker
import com.example.hansotbob.viewmodel.form.SharingFoodFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodShareFormScreen(navController: NavController, viewModel: SharingFoodFormViewModel = viewModel()) {
    var categoryExpanded by remember { mutableStateOf(false) }
    var quantityExpanded by remember { mutableStateOf(false) }
    var methodExpanded by remember { mutableStateOf(false) }

    val category by viewModel.category.collectAsState()
    val quantity by viewModel.quantity.collectAsState()
    val productionDate by viewModel.productionDate.collectAsState()
    val title by viewModel.title.collectAsState()
    val place by viewModel.place.collectAsState()
    val selectedMethod by viewModel.method.collectAsState()
    val price by viewModel.price.collectAsState()
    val description by viewModel.description.collectAsState()
    val imageUri by viewModel.imageUri.collectAsState() // Image URI를 ViewModel에서 관리

    val categoryOptions = listOf("한식", "분식", "중식", "일식", "양식")
    val quantityOptions = listOf("1인분", "2인분", "3인분", "4인분", "5인분", "6인분 이상")
    val methodOptions = listOf("직거래", "택배", "문고리")
    val errorMessage = remember { mutableStateOf("") }

    val context = LocalContext.current

    // 이미지 선택 결과를 처리하는 런처
    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            viewModel.setImageUri(it) // ViewModel에 선택된 이미지 URI 설정
        }
    }

    // 권한 요청 런처
    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            // 권한이 허용된 경우 이미지 선택을 시작합니다.
            imagePickerLauncher.launch("image/*")
        } else {
            // 권한이 거부된 경우 처리할 작업을 추가할 수 있습니다.
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = "밀키트 등록 화면",
                navController = navController,
                modifier = Modifier
                    .background(Color.White)
                    .border(BorderStroke(0.5.dp, Color.Gray), shape = RoundedCornerShape(0.dp))
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

            // 이미지 미리보기
            item {
                imageUri?.let { uri ->
                    val bitmap = loadImageFromUri(uri, context)
                    bitmap?.let {
                        androidx.compose.foundation.Image(
                            painter = BitmapPainter(it.asImageBitmap()),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // 이미지 업로드 버튼
            item {
                Button(
                    onClick = {
                        val permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            // 권한 요청
                            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        } else {
                            // 이미지 선택 시작
                            imagePickerLauncher.launch("image/*")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("사진 업로드")
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // 등록 버튼 및 오류 메시지
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
                                // 정상 등록
                                viewModel.uploadItem()
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
                    Spacer(modifier = Modifier.height(16.dp)) // 오류 메시지와 버튼 사이의 간격
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = errorMessage.value,
                            color = Color.Red,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // 오류 메시지와 다음 요소 사이의 간격
                }
            }
        }
    }
}

fun loadImageFromUri(uri: Uri, context: Context): Bitmap? {
    return try {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
