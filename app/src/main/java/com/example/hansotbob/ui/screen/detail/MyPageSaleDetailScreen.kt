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
import com.example.hansotbob.data.SaleItem

@Composable
fun MyPageSaleDetailScreen() {
    var selectedTab by remember { mutableStateOf(Tab.SALE) } // Initial tab selection

    // Selling and completed lists
    val sellingItems = listOf(
        SaleItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "판매 내역 아이템 1",
            itemPrice = "1000",
            detail = "판매 상세 내역 예시입니다.",
            sale = true,
            count = 5
        ),
        SaleItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "판매 내역 아이템 2",
            itemPrice = "1200",
            detail = "판매 상세 내역 예시입니다.",
            sale = true,
            count = 1
        ),
        SaleItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "거래완료 내역 아이템 3",
            itemPrice = "1500",
            detail = "판매 상세 내역 예시입니다.",
            saled = true,
            count = 2
        )
    )

    // Filtered lists based on tab selection
    val sellingItemsToShow = sellingItems.filter { it.sale }
    val completedItemsToShow = sellingItems.filter { it.saled }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, actionRow, tabBar, recyclerView) = createRefs()

        // Top bar
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
            // Back button
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )

            // Title
            Text(
                text = "판매 내역",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        // Write button
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(PrimaryColor),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(80.dp)
                .height(35.dp)
                .constrainAs(actionRow) {
                    end.linkTo(parent.end)
                    top.linkTo(topBar.bottom, margin = 10.dp)
                }
        ) {
            Text(
                text = "글쓰기",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        }

        // Tab bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tabBar) {
                    top.linkTo(actionRow.bottom, margin = 10.dp)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { selectedTab = Tab.SALE },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == Tab.SALE) PrimaryColor else Color.Transparent,
                    contentColor = if (selectedTab == Tab.SALE) Color.White else Color.Black
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
            ) {
                Text(
                    text = "판매중",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { selectedTab = Tab.SALED },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == Tab.SALED) PrimaryColor else Color.Transparent,
                    contentColor = if (selectedTab == Tab.SALED) Color.White else Color.Black
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
            ) {
                Text(
                    text = "거래완료",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Sale detail list based on selected tab
        val itemsToShow = when (selectedTab) {
            Tab.SALE -> sellingItemsToShow
            Tab.SALED -> completedItemsToShow
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(recyclerView) {
                    top.linkTo(tabBar.bottom)
                }
                .padding(top = 16.dp)
        ) {
            items(itemsToShow) { saleItem ->
                SaleItemCard(saleItem)
            }
        }
    }
}

enum class Tab {
    SALE,
    SALED
}

@Preview(showBackground = true)
@Composable
fun MyPageSaleDetailScreenPreview() {
    MyPageSaleDetailScreen()
}
