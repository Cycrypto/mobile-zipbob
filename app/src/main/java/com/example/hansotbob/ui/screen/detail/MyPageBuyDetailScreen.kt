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
import com.example.hansotbob.data.BuyItem

@Composable
fun MyPageBuyDetailScreen() {
    val purchaseItems = listOf(
        BuyItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "구매 내역 아이템 1",
            itemPrice = "1000",
            detail = "구매 상세 내역 예시입니다.",
            purchased = true,
            count = 1
        ),
        BuyItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "장바구니 내역 아이템 1",
            itemPrice = "1200",
            detail = "구매 상세 내역 예시입니다.",
            inCart = true,
            count = 2
        ),
        BuyItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "구매 내역 아이템 2",
            itemPrice = "1500",
            detail = "구매 상세 내역 예시입니다.",
            purchased = true,
            count = 3
        ),
        BuyItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "구매 내역 아이템 3",
            itemPrice = "1500",
            detail = "구매 상세 내역 예시입니다.",
            purchased = true,
            count = 4
        ),
        BuyItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "장바구니 내역 아이템 2",
            itemPrice = "1500",
            detail = "구매 상세 내역 예시입니다.",
            inCart  = true,
            count = 1
        )
    )

    var selectedTab by remember { mutableStateOf(BuyTab.IN_CART) } // Initial tab selection

    // Filtered lists based on tab selection
    val itemsInCartToShow = purchaseItems.filter { it.inCart }
    val itemsPurchasedToShow = purchaseItems.filter { it.purchased }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val (topBar, tabBar, recyclerView) = createRefs()

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
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )

            Text(
                text = "구매 내역",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tabBar) {
                    top.linkTo(topBar.bottom, margin = 10.dp)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { selectedTab = BuyTab.IN_CART },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == BuyTab.IN_CART) PrimaryColor else Color.Transparent,
                    contentColor = if (selectedTab == BuyTab.IN_CART) Color.White else Color.Black
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
            ) {
                Text(
                    text = "장바구니",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { selectedTab = BuyTab.PURCHASED },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == BuyTab.PURCHASED) PrimaryColor else Color.Transparent,
                    contentColor = if (selectedTab == BuyTab.PURCHASED) Color.White else Color.Black
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
            ) {
                Text(
                    text = "구매완료",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Sale detail list based on selected tab
        val itemsToShow = when (selectedTab) {
            BuyTab.IN_CART -> itemsInCartToShow
            BuyTab.PURCHASED -> itemsPurchasedToShow
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(recyclerView) {
                    top.linkTo(tabBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(top = 50.dp)
        ) {
            items(itemsToShow) { buyItem ->
                BuyItemCard(buyItem)
            }
        }
    }
}

enum class BuyTab {
    IN_CART,
    PURCHASED
}

@Preview(showBackground = true)
@Composable
fun MyPageBuyDetailScreenPreview() {
    MyPageBuyDetailScreen()
}
