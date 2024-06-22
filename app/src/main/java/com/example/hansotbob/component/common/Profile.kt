package com.example.hansotbob.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hansotbob.R
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.ui.theme.PrimaryColor
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.user.UserProfileViewModel

@Composable
fun ProfileOnMyPage(
    navController:NavController,
    viewModel: UserProfileViewModel = viewModel(factory = ViewModelFactory(FirebaseService())),
    modifier: Modifier){
    val user by viewModel.user.collectAsState()

    user?.let{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            val imagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it.imageUrl)
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = imagePainter,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = it.nickname,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "시흥시 공유자",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = it.userPoint.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Points",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "12",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Orders",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Edit Profile Button
            Button(
                onClick = { navController.navigate("profileEdit") },
                colors = ButtonDefaults.buttonColors(PrimaryColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "프로필 수정",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

        }
    }

}
