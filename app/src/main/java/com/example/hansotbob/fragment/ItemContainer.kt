package com.example.hansotbob.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hansotbob.component.CardView.MealCategoryCardWithBadge
import com.example.hansotbob.component.CardView.OverviewCard
import com.example.hansotbob.data.ListItem
import com.example.hansotbob.data.ItemDetail
import com.example.hansotbob.ui.screen.detail.ItemDetail
import com.example.hansotbob.ui.screen.detail.ReviewDetail
import com.example.hansotbob.ui.screen.PaymentDetail

@Composable
fun CategoryFragmentContainer(navController: NavController, items: List<ListItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        items(items) { item ->
            when (item) {
                is ListItem.MealContent -> {
                    MealCategoryCardWithBadge(
                        title = item.title,
                        date = item.recruit,
                        category = item.place,
                        points = item.price,
                        imagePainter = painterResource(id = item.imagePainterId),
                        isNew = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(
                                    "foodshare/detail/${item.title}/${item.recruit}/${item.place}/${item.price}"
                                )
                            }
                    )
                }
                is ListItem.Overview ->{
                    OverviewCard(
                        imageRes = item.imageRes,
                        name = item.name,
                        category = item.category,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clickable{
                                navController.navigate(
                                    "detail/${item.name}/${item.category}/${item.imageRes}"
                                )
                            }
                    )

                }
                is ListItem.MealkitsContent -> {
                    MealCategoryCardWithBadge(
                        title = item.title,
                        date = item.recruit,
                        category = item.place,
                        points = item.price,
                        imagePainter = painterResource(id = item.imagePainterId),
                        isNew = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(
                                    "mealkit/detail/${item.title}/${item.recruit}/${item.place}/${item.price}"
                                )
                            }
                    )
                }
                /*is ItemDetail -> {
                    ItemDetail(
                        itemDetail = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                // 클릭 시 이동할 경로를 설정
                                navController.navigate(
                                    "item/detail/${item.imagePainterId}/${item.productName}/${item.itemPrice}/${item.detail}"
                                )
                            }
                    )
                }
                is ReviewDetail -> {
                    ReviewDetail(
                        reviewDetail = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                // 클릭 시 이동할 경로를 설정
                                navController.navigate(
                                    "review/detail/${item.userName}/${item.reviewRating}/${item.detail}"
                                )
                            }
                    )
                }
                is ListItem.PaymentDetail -> {
                    PaymentDetail(
                        paymentDetail = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                // 클릭 시 이동할 경로를 설정
                                navController.navigate(
                                    "review/detail/${item.productName}/${item.itemCounts}/${item.itemPrice}"
                                )
                            }
                    )
                }*/
            }
        }
    }
}