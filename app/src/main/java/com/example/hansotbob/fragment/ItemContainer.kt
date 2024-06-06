package com.example.hansotbob.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.hansotbob.R
import com.example.hansotbob.component.CardView.MealCategoryCard
import com.example.hansotbob.data.ListItem

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
                    MealCategoryCard(
                        title = item.title,
                        recruit = item.recruit,
                        place = item.place,
                        price = item.price,
                        imagePainter = painterResource(id = item.imagePainterId),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(
                                    "detail/${item.title}/${item.recruit}/${item.place}/${item.price}"
                                )
                            }
                    )
                }
                is ListItem.Restaurant -> {
                    print("hello")
                }
            }
        }
    }
}
