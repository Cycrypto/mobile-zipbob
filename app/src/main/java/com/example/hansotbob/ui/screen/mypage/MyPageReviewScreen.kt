package com.example.hansotbob.ui.screen.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.component.common.detail.ReviewList
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.ui.theme.PrimaryColor
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.screen.detail.ReviewViewModel


@Composable
fun MyPageReviewScreen(
    navController: NavController,
    reviewModel: ReviewViewModel = viewModel(
        factory = ViewModelFactory(FirebaseService())
    )
) {
    val reviews by reviewModel.reviews.collectAsState()

    LaunchedEffect(key1 = Unit) {
        reviewModel.loadReviewsForCurrentUser()
    }

    Column(
        modifier = Modifier
            .padding(16.dp) // Add padding here
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                // Customized Top Bar similar to the first image
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color(0xFFFFA000))
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    // Back Button
                    Image(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = "Back Button",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 8.dp)
                            .clickable { navController.popBackStack() }
                    )

                    // Title
                    Text(
                        text = "내가 쓴 리뷰",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 40.dp), // Adjust end padding to align center
                        textAlign = TextAlign.Center
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                // Review List
                ReviewList(reviews = reviews)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageReviewScreenPreview() {
    MyPageReviewScreen(rememberNavController())
}
