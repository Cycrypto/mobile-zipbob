package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hansotbob.data.BuyItem
import androidx.compose.ui.graphics.Shape
import com.example.hansotbob.R

@Composable
fun BuyItemCard(
    buyItem: BuyItem,
    modifier: Modifier = Modifier,
    border: Dp = 1.dp,
    shape: Shape = RoundedCornerShape(8.dp)
) {
    val totalPrice = buyItem.itemPrice.toInt() * buyItem.count

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .border(border, Color.LightGray, shape)
            .padding(16.dp)
    ) {
        Column {
            // Status Text above image
            if (buyItem.inCart || buyItem.purchased) {
                val statusText = if (buyItem.inCart) "장바구니" else "구매완료"
                Text(
                    text = statusText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Product Image
                Image(
                    painter = painterResource(id = buyItem.imagePainterId),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape) // 이미지에도 테두리 모양 적용
                )

                // Spacer between image and details
                Spacer(modifier = Modifier.width(16.dp))

                // Product Details and Quantity Input
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = buyItem.productName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Display total price based on quantity and item price
                        Text(
                            text = "상품가격: ${buyItem.itemPrice}pt",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        // Quantity Input
                        Text(
                            text = "수량: ${buyItem.count}",
                            fontSize = 14.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "총 가격: $totalPrice pt",
                        textAlign = TextAlign.End,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = buyItem.detail,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun BuyItemCardPreview() {
    BuyItemCard(
        buyItem = BuyItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "상품명",
            itemPrice = "1000",
            detail = "구매상세내역 예시입니다.",
            purchased = true, // 예시로 구매완료 상태 설정
            count = 2
        )
    )
}
