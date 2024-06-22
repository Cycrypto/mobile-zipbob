package com.example.hansotbob.ui.screen.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.hansotbob.component.common.ProfileOnMyPage
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.example.hansotbob.ui.theme.PrimaryColor

@Composable
fun MyPageScreen(navController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, profileContainer, transactionList, editProfileButton) = createRefs()

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
                    .clickable { navController.popBackStack() }
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
        ProfileOnMyPage(
            navController,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(profileContainer) { top.linkTo(topBar.bottom) }
        )

        // Transaction List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(transactionList) {
                    top.linkTo(profileContainer.bottom)
                    bottom.linkTo(editProfileButton.top, margin = 16.dp)
                }
        ) {
            items(items = listOf(
                "내가 구매한 목록" to "buy",
                "내가 판매한 목록" to "sales",
                "내가 받은 리뷰" to "myReviews",
                "내가 쓴 리뷰" to "receivedReviews",
            )) { (label, route) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp) // Increased padding for larger size
                        .clickable { navController.navigate(route) }
                ) {
                    val icon = when (route) {
                        "buy" -> Icons.Default.Payment
                        "sales" -> Icons.Default.Sell
                        "myReviews" -> Icons.Default.Reviews
                        "receivedReviews" -> Icons.Default.RateReview
                        else -> Icons.Default.Place
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon for $label",
                        modifier = Modifier.size(32.dp), // Increased icon size
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = label,
                        fontSize = 18.sp, // Increased text size
                        modifier = Modifier.padding(start = 24.dp) // Increased padding between icon and text
                    )
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    HansotbobTheme{
        val navController = rememberNavController()
        MyPageScreen(navController)
    }
}
