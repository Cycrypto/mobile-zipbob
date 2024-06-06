package com.example.hansotbob.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.hansotbob.component.CardView.MealCategoryCard
import com.example.hansotbob.component.common.HansotThemeNavigationBar
import com.example.hansotbob.R
import com.example.hansotbob.data.MealContent
import com.example.hansotbob.ui.theme.HansotbobTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HansotbobTheme{
                MainScreen()
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var loadView by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AddressBar()
                SearchBar()
                CategoryFragmentContainer()
                if (loadView) {
                    ComposableFunction()
                }
            }
        },
        bottomBar = {
            HansotThemeNavigationBar()
        }
    )
}

@Composable
fun AddressBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Text(
            text = "경기도 시흥시 정왕1동",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_pen),
            contentDescription = null
        )
    }
}

@Composable
fun SearchBar() {
    BasicTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        singleLine = true,
        cursorBrush = SolidColor(Color.Black),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (innerTextField.toString().isEmpty()) {
                        Text("Restaurants, food, drinks", style = MaterialTheme.typography.bodyLarge)
                    }
                    innerTextField()
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_find),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun CategoryFragmentContainer() {
    val items = listOf(
        MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000"),
        MealContent(R.drawable.food_image, "집밥 가져가실분", "모집중", "시흥시 정왕동 산기대학로", "1000")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        items(items) { item ->
            MealCategoryCard(
                title = item.title,
                recruit = item.recruit,
                place = item.place,
                price = item.price,
                imagePainter = painterResource(id = item.imagePainterId),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}


@Composable
fun ComposableFunction() {
    Text("appbar content")
}
