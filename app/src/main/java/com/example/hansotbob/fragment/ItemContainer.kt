package com.example.hansotbob.fragment

import android.util.Log
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
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.dto.MealContent
import com.example.hansotbob.dto.Overview
import com.example.hansotbob.dto.MealkitsContent

@Composable
fun CategoryFragmentContainer(navController: NavController, items: List<Any>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        items(items) { item ->
            when (item) {
                is FoodShareContent -> {
                    Log.d("ItemContainer", "item : ${item}")
                    MealCategoryCardWithBadge(
                        title = item.title,
                        date = item.productionDate,
                        category = item.category,
                        points = item.price,
                        place = item.place,
                        state = item.state,
                        imagePainter = painterResource(id = item.imagePainterId),
                        // isNew = item.isNew,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(
                                    "foodshare/detail/${item.itemId}"
                                )
                            }
                    )
                }
                is Overview -> {
                    OverviewCard(
                        imageRes = item.imageRes,
                        name = item.name,
                        category = item.category,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(
                                    "detail/${item.name}/${item.category}/${item.imageRes}"
                                )
                            }
                    )
                }
                is MealkitsContent -> {
                    MealCategoryCardWithBadge(
                        title = item.title,
                        date = item.productionDate,
                        category = item.category,
                        points = item.price,
                        place = item.place,
                        state = item.state, // Assuming `state` is a field in `MealkitsContent`
                        imagePainter = painterResource(id = item.imagePainterId), // Assuming `imagePainterId` is a field in `MealkitsContent`
                        isNew = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(
                                    "mealkit/detail/${item.itemId}"
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
