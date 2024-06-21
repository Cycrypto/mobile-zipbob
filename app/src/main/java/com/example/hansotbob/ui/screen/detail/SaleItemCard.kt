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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hansotbob.data.SaleItem
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.example.hansotbob.R

@Composable
fun SaleItemCard(
    saleItem: SaleItem,
    modifier: Modifier = Modifier,
    border: Dp = 1.dp, // Border thickness
    shape: Shape = RoundedCornerShape(8.dp) // Border shape (rounded corners by default)
) {
    val totalPrice = saleItem.itemPrice.toInt() * saleItem.count

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .border(border, Color.LightGray, shape)
            .padding(16.dp)
    ) {
        Column {
            if (saleItem.sale) {
                Text(
                    text = "판매중",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            } else if (saleItem.saled) {
                Text(
                    text = "거래완료",
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
                    painter = painterResource(id = saleItem.imagePainterId),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape) // Apply shape to the image
                )

                // Spacer between image and details
                Spacer(modifier = Modifier.width(16.dp))

                // Product Details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = saleItem.productName,
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
                            text = "상품가격: ${saleItem.itemPrice}pt",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        // Quantity Input
                        Text(
                            text = "수량: ${saleItem.count}",
                            fontSize = 14.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Text(
                        text = "총 가격: $totalPrice pt",
                        textAlign = TextAlign.End,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = saleItem.detail,
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
fun SaleItemCardPreview() {
    SaleItemCard(
        saleItem = SaleItem(
            imagePainterId = R.drawable.ic_mypage,
            productName = "상품명",
            itemPrice = "1000",
            detail = "판매상세내역 예시입니다.",
            sale = true, // Example sale item
            count = 2
        )
    )
}
