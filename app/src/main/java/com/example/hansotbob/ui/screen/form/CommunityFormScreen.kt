import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.ui.theme.HansotbobTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityFormScreen(navController: NavController) {
    val formData = remember { mutableStateOf(CommunityFormData()) }
    var expanded by remember { mutableStateOf(false) }
    val categories = listOf("집밥공유", "식료품공유")

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        formData.value = formData.value.copy(imageUri = uri)
    }

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            item {
                TopAppBar(
                    colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.primary),
                    title = { Text("파티 모집") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                OutlinedTextField(
                    value = formData.value.title,
                    onValueChange = { formData.value = formData.value.copy(title = it) },
                    label = { Text(text = "제목 입력") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                            text = formData.value.category.ifEmpty { "카테고리 선택" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                onClick = {
                                    formData.value = formData.value.copy(category = category)
                                    expanded = false
                                },
                                text = { Text(category) }
                            )
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("총비용")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = formData.value.totalCost,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() }) {
                                formData.value = formData.value.copy(totalCost = it)
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("모집인원")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = formData.value.participants,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() }) {
                                formData.value = formData.value.copy(participants = it)
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("거래장소")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = formData.value.location,
                        onValueChange = { formData.value = formData.value.copy(location = it) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                OutlinedTextField(
                    value = formData.value.description,
                    onValueChange = { formData.value = formData.value.copy(description = it) },
                    label = { Text("한줄 설명") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                formData.value.imageUri?.let { uri ->
                    val bitmap = loadImageFromUri(uri, context)
                    bitmap?.let {
                        Image(
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
            item {
                Button(
                    onClick = {
                        val permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        if (permissionCheck != PermissionChecker.PERMISSION_GRANTED) {
                            // Request permission here
                        } else {
                            imagePickerLauncher.launch("image/*")
                        }
                    },
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

fun loadImageFromUri(uri: Uri, context: Context): Bitmap? {
    return try {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Preview
@Composable
private fun CommunityFormScreenPreview() {
    HansotbobTheme {
        val navController = rememberNavController()
        CommunityFormScreen(navController)
    }
}

data class CommunityFormData(
    var title: String = "",
    var category: String = "",
    var totalCost: String = "",
    var participants: String = "",
    var location: String = "",
    var description: String = "",
    var imageUri: Uri? = null
)
