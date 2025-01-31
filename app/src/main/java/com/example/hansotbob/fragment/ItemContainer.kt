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
import com.example.hansotbob.CommunityCardWithBadge
import com.example.hansotbob.R
import com.example.hansotbob.component.CardView.MealCategoryCardWithBadge
import com.example.hansotbob.component.CardView.OverviewCard
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.dto.IngredientShareContent
import com.example.hansotbob.dto.Overview
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.viewmodel.screen.IngredientShreViewModel

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
                            },
                        rating = 4
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
                        state = item.state,
                        imagePainter = painterResource(id = item.imagePainterId),
                        isNew = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(
                                    "mealkit/detail/${item.itemId}"
                                )
                            },
                        rating = 4
                    )
                }
                is IngredientShareContent -> {
                    Log.d("Ingredient", "start badge : $item")
                    CommunityCardWithBadge(
                        title = item.title,
                        author = item.author,
                        itemId = item.itemId,
                        imagePainter = painterResource(id = R.drawable.community_image),
                        totalPeople = item.totalPeople,
                        points = item.totalCost,
                        location = item.location
                    )
                }
            }
        }
    }
}
