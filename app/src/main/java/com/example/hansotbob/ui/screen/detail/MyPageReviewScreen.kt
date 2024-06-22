package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.PrimaryColor
import com.example.hansotbob.data.ReviewDetail
import com.example.hansotbob.data.User

@Composable
fun MyPageReviewScreen() {
    val user = User(
        userName = "진소희",
        nickname = "감자",
        userPoint = 3000,
        imagePainterId = R.drawable.ic_mypage
    )
    // 리뷰내역 데이터 리스트
    val reviewItems = listOf(
        ReviewDetail(
            imagePainterId = R.drawable.ic_mypage,
            userName = "닉네임 1",
            reviewRating = 4.0f,
            detail = "리뷰 상세 내역 예시입니다.",
            productName = "나눔받은 음식명"
        ),
        ReviewDetail(
            imagePainterId = R.drawable.ic_mypage,
            userName = "닉네임 2",
            reviewRating = 3.5f,
            detail = "리뷰 상세 내역 예시입니다.",
            productName = "나눔받은 음식명"
        ),
        ReviewDetail(
            imagePainterId = R.drawable.ic_mypage,
            userName = "닉네임 3",
            reviewRating = 1.5f,
            detail = "리뷰 상세 내역 예시입니다.",
            productName = "나눔받은 음식명"
        ),
        ReviewDetail(
            imagePainterId = R.drawable.ic_mypage,
            userName = user.nickname,
            reviewRating = 4.5f,
            detail = "리뷰 상세 내역 예시입니다.",
            productName = "나눔받은 음식 이름"
        ),
        ReviewDetail(
            imagePainterId = R.drawable.ic_mypage,
            userName = user.nickname,
            reviewRating = 3.0f,
            detail = "리뷰 상세 내역 예시입니다.",
            productName = "나눔받은 음식 이름"
        ),
        ReviewDetail(
            imagePainterId = R.drawable.ic_mypage,
            userName = user.nickname,
            reviewRating = 4.0f,
            detail = "리뷰 상세 내역 예시입니다.",
            productName = "나눔받은 음식 이름"
        )
    )

    var selectedTab by remember { mutableStateOf(ReviewTab.MY_REVIEW) }

    val myReviewToShow = reviewItems.filter { it.userName == user.nickname }
    val otherReviewToShow = reviewItems.filter { it.userName != user.nickname }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, actionRow, tabBar, recyclerView) = createRefs()

        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(PrimaryColor)
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                }
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
                text = "리뷰",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        // Action Row (User Info)
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .constrainAs(actionRow) {
                    end.linkTo(parent.end)
                    top.linkTo(topBar.bottom, margin = 10.dp)
                }
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_mypage),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${user.nickname} 님",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "도움 ${myReviewToShow.size} 명",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tabBar) {
                    top.linkTo(actionRow.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { selectedTab = ReviewTab.MY_REVIEW },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == ReviewTab.MY_REVIEW) PrimaryColor else Color.Transparent,
                    contentColor = if (selectedTab == ReviewTab.MY_REVIEW) Color.White else Color.Black
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
            ) {
                Text(
                    text = "나의리뷰",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { selectedTab = ReviewTab.OTHER_REVIEW },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == ReviewTab.OTHER_REVIEW) PrimaryColor else Color.Transparent,
                    contentColor = if (selectedTab == ReviewTab.OTHER_REVIEW) Color.White else Color.Black
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
            ) {
                Text(
                    text = "받은리뷰",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Sale detail list based on selected tab
        val itemsToShow = when (selectedTab) {
            ReviewTab.MY_REVIEW -> myReviewToShow
            ReviewTab.OTHER_REVIEW -> otherReviewToShow
        }

        // RecyclerView (or LazyColumn in Compose)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 55.dp)
                .constrainAs(recyclerView) {
                    top.linkTo(tabBar.bottom, margin = 55.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            items(itemsToShow) { reviewDetail ->
                ReviewDetail(reviewDetail = reviewDetail)
            }
        }
    }
}

enum class ReviewTab {
    MY_REVIEW,
    OTHER_REVIEW
}

@Preview(showBackground = true)
@Composable
fun MyPageReviewScreenPreview() {
    MyPageReviewScreen()
}
