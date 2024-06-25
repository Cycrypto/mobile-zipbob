package com.example.hansotbob.ui.screen.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.PrimaryColor
import com.example.hansotbob.data.ItemDetail
import com.example.hansotbob.ui.screen.detail.ItemDetail

@Composable
fun MyPageBuyDetailScreen(navController: NavController) {
    //구매내역 데이터 리스트
    val purchaseItems = listOf(
        ItemDetail(
            imagePainterId = R.drawable.ic_mypage,
            productName = "구매 내역 아이템 1",
            itemPrice = "가격: 1000pt",
            detail = "구매 상세 내역 예시입니다."
        ),
        ItemDetail(
            imagePainterId = R.drawable.ic_mypage,
            productName = "구매 내역 아이템 2",
            itemPrice = "가격: 1200pt",
            detail = "구매 상세 내역 예시입니다."
        ),
        ItemDetail(
            imagePainterId = R.drawable.ic_mypage,
            productName = "구매 내역 아이템 3",
            itemPrice = "가격: 1500pt",
            detail = "구매 상세 내역 예시입니다."
        )
    )
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, recyclerView) = createRefs()

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
                    .clickable{ navController.popBackStack() }
            )

            // Title
            Text(
                text = "구매 내역",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        // RecyclerView (or LazyColumn in Compose)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(recyclerView) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(top = 32.dp)
        ) {
            items(purchaseItems) { itemDetail ->
                ItemDetail(itemDetail = itemDetail)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageBuyDetailScreenPreview() {
    val navController = rememberNavController()
    MyPageBuyDetailScreen(navController)
}
