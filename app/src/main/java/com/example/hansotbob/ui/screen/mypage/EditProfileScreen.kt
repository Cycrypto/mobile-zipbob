package com.example.hansotbob.ui.screen.mypage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hansotbob.R
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.user.UserProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController, viewModel: UserProfileViewModel = viewModel(factory = ViewModelFactory(FirebaseService()))) {
    val user by viewModel.user.collectAsState()
    val uploading by viewModel.uploading.collectAsState()
    var newNickname by remember { mutableStateOf(user?.nickname ?: "") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.uploadUserImage(it)
            imageUri = it
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "프로필") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.updateNickname(newNickname)
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
                    }
                }
            )
        }
    ) { innerPadding ->
        user?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                if (uploading) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                ) {
                    val imagePainter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUri ?: it.imageUrl)
                            .build()
                    )
                    Image(
                        painter = imagePainter,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { launcher.launch("image/*") },
                        contentScale = ContentScale.Crop
                    )
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Change Profile Image",
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(4.dp)
                            .align(Alignment.BottomEnd)
                            .clickable { launcher.launch("image/*") }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "YOUR EMAIL",
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                )
                TextField(
                    value = it.userName,
                    onValueChange = {},
                    label = { Text("User Name") },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "YOUR PASSWORD",
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                )
                TextField(
                    value = "**********",
                    onValueChange = {},
                    label = { Text("Password") },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = newNickname,
                    onValueChange = { newNickname = it },
                    label = { Text("Nickname") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}
