package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.hansotbob.component.common.detail.ContentRow
import com.example.hansotbob.component.common.detail.DetailRow
import com.example.hansotbob.component.common.detail.PostAuthordata
import com.example.hansotbob.dto.MealkitsContent
import com.example.hansotbob.service.FirebaseService
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
    val foodshare by viewModel.item.collectAsState()   // 이름만 바꾸고 임시로 mealkit에 연결

    viewModel.loadItem(itemId)

    // val authorData by viewModel.authorData.collectAsState()
    // viewModel.loadAuthorData(authorId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "음식 공유 세부 정보") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        foodshare?.let { item ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.food_image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 16.dp)
                    )
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
                            fontSize = 24.sp
                        )
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    DetailRow(label = "거래 장소", value = item.place)
                    DetailRow(label = "가격", value = item.price)
                    DetailRow(label = "카테고리", value = item.category)
                    DetailRow(label = "양", value = item.quantity)
                    DetailRow(label = "제조일자", value = item.productionDate)
                    DetailRow(label = "거래 방법", value = item.method)
                    Text(
                        text = item.description,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    // PostAuthordata(authorData = authorData)
                }
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
fun FoodShareContent(
    title: String,
    place: String,
    price: String,
    foodType: String,
    category: String,
    quantity: String,
    productionDate: String,
    exchangeMethod: String,
    description: String,
    state: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state == 1) {
                Text(
                    text = "[거래중]",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                )
            } else if (state == 2) {
                Text(
                    text = "[거래완료]",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(label = "거래 장소", content = place)
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(label = "가격", content = price)
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(label = "음식 종류", content = foodType)
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(label = "카테고리", content = category)
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(label = "양", content = quantity)
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(label = "제조일자", content = productionDate)
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(label = "거래 방법", content = exchangeMethod)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}



 //기존 코드

//@Composable
//fun FoodShareDetailScreen(
//    navController: NavHostController,
//    itemId: String,
//    viewModel: FoodShareDetailViewModel = viewModel()
//) {
//    val item by viewModel.item.collectAsState()
//
//    LaunchedEffect(itemId) {
//        viewModel.loadItem(itemId)
//    }
//
//    if (item != null) {
//        val pagerState = rememberPagerState(pageCount = { 3 })
//
//        Scaffold { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//            ) {
//                HorizontalPager(
//                    state = pagerState,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(400.dp)
//                ) { page ->
//                    Image(
//                        painter = painterResource(id = item!!.imagePainterId),
//                        contentDescription = "Image $page",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    IconWithText(iconId = R.drawable.ic_add, text = "5.0 (2k)")
//                    IconWithText(iconId = R.drawable.ic_add, text = "80 Cal")
//                    IconWithText(iconId = R.drawable.ic_add, text = "Fri 26.12")
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                        .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
//                        .padding(16.dp)
//                ) {
//                    Text(
//                        text = item!!.title,
//                        style = MaterialTheme.typography.titleLarge,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "Recruit: ${item!!.recruit}",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "Place: ${item!!.place}",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "Price: ${item!!.price}",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(
//                    onClick = { /* TODO: Add to Cart */ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                        .height(56.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
//                ) {
//                    Text(text = "Add to Cart", color = Color.White)
//                }
//            }
//        }
//    } else {
//        Text("Item not found")
//    }
//}
//
//@Composable
//fun IconWithText(iconId: Int, text: String) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Icon(
//            painter = painterResource(id = iconId),
//            contentDescription = null,
//            tint = MaterialTheme.colorScheme.primary
//        )
//        Spacer(modifier = Modifier.width(4.dp))
//        Text(text = text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
//    }
//}
//
//@Preview
//@Composable
//private fun PreviewDetailScreen() {
//    HansotbobTheme {
//        val navController = rememberNavController()
//        FoodShareDetailScreen(
//            navController = navController,
//            itemId = "dummyId"
//        )
//    }
//}
