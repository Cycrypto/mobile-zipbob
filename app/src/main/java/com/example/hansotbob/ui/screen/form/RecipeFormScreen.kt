package com.example.hansotbob.ui.screen.form

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.ui.theme.HansotbobTheme
import java.io.File
import java.io.FileOutputStream

fun saveBitmapToFile(bitmap: Bitmap, context: Context): String {
    val filename = "selected_recipe_image_${System.currentTimeMillis()}.png"
    val file = File(context.filesDir, filename)
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file.absolutePath
}

fun loadImageFromUri(uri: Uri, context: Context): Bitmap? {
    return try {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

data class RecipeFormData(
    var title: String = "",
    var ingredients: String = "",
    var recipe: String = "",
    var imageUris: List<Uri> = emptyList(),
    var imagePaths: List<String> = emptyList()
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeFormScreen(navController: NavController) {
    val formData = remember { mutableStateOf(RecipeFormData()) }

    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = loadImageFromUri(it, context)
            bitmap?.let { bmp ->
                val path = saveBitmapToFile(bmp, context)
                formData.value = formData.value.copy(
                    imageUris = formData.value.imageUris + it,
                    imagePaths = formData.value.imagePaths + path
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.primary),
                title = { Text("레시피 추가") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                OutlinedTextField(
                    value = formData.value.title,
                    onValueChange = { formData.value = formData.value.copy(title = it) },
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
                    Text("재료")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = formData.value.ingredients,
                        onValueChange = { formData.value = formData.value.copy(ingredients = it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("레시피")
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = formData.value.recipe,
                        onValueChange = { formData.value = formData.value.copy(recipe = it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                if (formData.value.imageUris.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        items(formData.value.imageUris) { uri ->
                            val bitmap = loadImageFromUri(uri, context)
                            bitmap?.let {
                                Image(
                                    painter = BitmapPainter(it.asImageBitmap()),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(MaterialTheme.colorScheme.background)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

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

@Preview
@Composable
private fun PreviewRecipeFormScreen() {
    HansotbobTheme {
        val navController = rememberNavController()
        RecipeFormScreen(navController)
    }
}
