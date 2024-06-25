package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.component.common.ButtonBar
import com.example.hansotbob.component.common.detail.DetailRow
import com.example.hansotbob.component.common.detail.PostAuthordata
import com.example.hansotbob.component.common.detail.ReviewSection
import com.example.hansotbob.dto.AuthorData
import com.example.hansotbob.dto.FoodShareContent
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.dto.Review
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.ui.theme.DetailLabelColor
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.screen.detail.FoodShareDetailViewModel
import com.example.hansotbob.viewmodel.screen.detail.MealkitDetailViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodShareDetailScreen(
    navController: NavController,
    itemId: String,
    viewModel: FoodShareDetailViewModel = viewModel(
        factory = ViewModelFactory(FirebaseService())
    )
) {
    val images = listOf(
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image // 실제 이미지 리소스를 추가
    )
    val pagerState = rememberPagerState(pageCount = { images.size })
    val foodShare by viewModel.item.collectAsState()
    viewModel.loadItem(itemId)


    Scaffold(
        topBar = {
            AppBar(
                title = "음식 공유 상세 화면",
                navController = navController,
                modifier = Modifier
                    .background(Color.White)
                    .border(BorderStroke(0.5.dp, Color.Gray), shape = RectangleShape)
            )
        }
    ) { innerPadding ->
        foodShare?.let { item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) { page ->
                    Image(
                        painter = painterResource(id = images[page]),
                        contentDescription = "Image $page",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                FoodShareContentDetail(item = item)
                Spacer(modifier = Modifier.height(16.dp))
                PostAuthordata(foodShare!!.authorId)
                ButtonBar(onContactSellerClick = {/* contact seller */}, onBuyClick = {/* buy click */})
                Spacer(modifier = Modifier.height(16.dp))
            }
        } ?: run {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun FoodShareContentDetail(
    item: FoodShareContent,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (item.state == 0) "[거래중]" else "[거래 완료]",
                color = if (item.state == 0) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.inversePrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.price}P",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            DetailRow(label = "거래 장소", value = item.place)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "카테고리", value = item.category)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "양", value = item.quantity)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "제조일자", value = item.productionDate)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "거래 방법", value = item.method)
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(label = "작성자", value = item.authorId)
        }
        Text(
            text = item.description,
            fontSize = 14.sp
        )
    }
}



