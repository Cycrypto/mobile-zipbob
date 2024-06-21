package com.example.hansotbob.ui.screen.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.PrimaryColor
import com.example.hansotbob.ui.theme.WarmPrimaryColor

@Composable
fun MyPageScreen(navController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, profileContainer, transactionHeader, transactionList, editProfileButton) = createRefs()

        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(PrimaryColor)
                .fillMaxWidth()
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Back Button
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )

            // Title
            Text(
                text = "마이 페이지",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        // Profile Container
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White)
                .constrainAs(profileContainer) {
                    top.linkTo(topBar.bottom)
                }
        ) {
            val (profileImage, nickname, points) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(80.dp)
                    .constrainAs(profileImage) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(parent)
                    },
                contentScale = ContentScale.Crop
            )

            Text(
                text = "닉네임",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .constrainAs(nickname) {
                        start.linkTo(profileImage.end)
                        centerVerticallyTo(profileImage)
                    }
            )

            Text(
                text = "포인트",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(points) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(profileImage)
                }
            )
        }

        // Transaction Header
        Text(
            text = "나의 거래",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(transactionHeader) {
                    top.linkTo(profileContainer.bottom)
                }
        )

        // Transaction List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(transactionList) {
                    top.linkTo(transactionHeader.bottom)
                    bottom.linkTo(editProfileButton.top, margin = 16.dp)
                }
        ) {
            items(items = listOf(
                "구매내역" to "buy",
                "판매내역" to "sales",
                "내가 쓴 리뷰" to "myReviews",
                "내가 받은 리뷰" to "receivedReviews"
            )) { (label, route) ->
                Button(
                    onClick = { navController.navigate(route) },
                    colors = buttonColors(WarmPrimaryColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = label,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }
        }

        // Edit Profile Button
        Button(
            onClick = { navController.navigate("profileEdit") },
            colors = buttonColors(PrimaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(editProfileButton) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
        ) {
            Text(
                text = "프로필 수정",
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    val navController = rememberNavController()
    MyPageScreen(navController)
}
