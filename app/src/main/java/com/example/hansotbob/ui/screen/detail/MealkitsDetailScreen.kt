package com.example.hansotbob.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hansotbob.R

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hansotbob.component.CardView.ReviewCard
import com.example.hansotbob.ui.theme.ReviewInputColor
import com.example.hansotbob.component.common.ButtonBar
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.example.hansotbob.dto.Review
import com.example.hansotbob.viewmodel.screen.detail.MealkitDetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.component.common.detail.DetailRow
import com.example.hansotbob.component.common.detail.PostAuthordata
import com.example.hansotbob.component.common.detail.ReviewSection
import com.example.hansotbob.component.common.detail.ReviewTextField
import com.example.hansotbob.dto.AuthorData
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.ui.theme.DetailLabelColor
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.screen.detail.ReviewViewModel
import com.google.firebase.auth.FirebaseAuth

//import com.google.android.play.integrity.internal.i

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealkitsDetailScreen(
    navController: NavController,
    itemId: String,
    viewModel: MealkitDetailViewModel = viewModel(
        factory = ViewModelFactory(FirebaseService())
    ),
    reviewModel: ReviewViewModel = viewModel(
        factory= ViewModelFactory(FirebaseService()))
) {
    val images = listOf(
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image // 실제 이미지 리소스를 추가
    )
    val currentUser = FirebaseAuth.getInstance().currentUser
    val buyerId = currentUser?.uid ?: "anonymous"
    val pagerState = rememberPagerState(pageCount = { images.size })
    val mealkit by viewModel.mealkit.collectAsState()

    LaunchedEffect(itemId) {
        Log.d("MealkitsDetailScreen", "Calling loadMealkit for itemId: $itemId")
        viewModel.loadMealkit(itemId)
    }

    LaunchedEffect(itemId) {
        Log.d("ReviewModel", "Calling loadReviews for itemId: $itemId")
        reviewModel.loadReviews(itemId)
    }

    val reviewListState by reviewModel.reviews.collectAsState()
    val reviewList = reviewListState

    val averageRating = calculateAverageRating(reviewList)

    Scaffold(
        topBar = {
            AppBar(
                title = "밀키트 상세 화면",
                navController = navController,
                modifier = Modifier
                    .background(Color.White)
                    .border(BorderStroke(0.5.dp, Color.Gray), shape = RectangleShape)
            )
        }
    ) { innerPadding ->
        mealkit?.let { item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) { page ->
                    Image(
                        painter = painterResource(id = images[page]),
                        contentDescription = "Image $page",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                MealkitContent(item = item, averageRating = averageRating)
                Spacer(modifier = Modifier.height(16.dp))
                PostAuthordata(mealkit!!.authorId)
                ButtonBar(navController = navController,
                    authorId = mealkit!!.authorId,
                    buyerId = buyerId,
                    itemId = itemId)
                Spacer(modifier = Modifier.height(16.dp))
                ReviewSection(
                    reviewList = reviewList,
                    onReviewSubmit = { reviewContent, rating ->
                        reviewModel.uploadReview(itemId, reviewContent, rating)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        } ?: run {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun MealkitContent(
    item: MealkitsContent,
    averageRating: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (item.state == 0) "[거래중]" else "[거래 완료]",
                color = if (item.state == 0) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.inversePrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.price}P",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Text(
                text = "·",
                fontWeight = FontWeight.Bold,
                color = DetailLabelColor,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 6.dp)
            )
            Text(
                text = "★${"%.1f".format(averageRating)}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            DetailRow(label = "거래 장소", value = item.place)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "카테고리", value = item.category)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "양", value = item.quantity)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "제조일자", value = item.productionDate)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "거래 방법", value = item.method)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Text(
            text = item.description,
            fontSize = 14.sp
        )
    }
}

fun calculateAverageRating(reviews: List<Review>): Float {
    if (reviews.isEmpty()) {
        return 5.0f
    }

    val totalRating = reviews.map { it.rating }.sum()
    return totalRating / reviews.size
}


