package com.example.hansotbob.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hansotbob.component.CardView.ReviewCard
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.ui.theme.ReviewInputColor
import com.example.hansotbob.component.common.ButtonBar
import com.example.hansotbob.dto.Review
import com.example.hansotbob.viewmodel.screen.detail.MealkitDetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.google.android.play.integrity.internal.i

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealkitsDetailScreen(
    navController: NavController,
    itemId: String,
    viewModel: MealkitDetailViewModel = viewModel(
        factory = ViewModelFactory(FirebaseService())
    )
) {
    val mealkit by viewModel.mealkit.collectAsState()

    viewModel.loadMealkit(itemId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "밀키트 상세 정보") },
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
        mealkit?.let { item ->
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
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}

@Composable
fun MealkitContent(
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

@Composable
fun ContentRow(label: String, content: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(2f)
        )
    }
}



@Composable
fun PostMetadata(
    metadata: Metadata,
    modifier: Modifier = Modifier
) {
    Column {
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 0.dp)
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(16.dp)
                .semantics(mergeDescendants = true) {}
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = LocalContentColor.current
            )
            Spacer(Modifier.width(8.dp))
            Column {
                Text(
                    text = metadata.author.name,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

data class Metadata(
    val author: Author,
    val date: String,
)

data class Author(
    val name: String
)


@Composable
fun ReviewDummyList(): List<Review> {
    return listOf(
        Review("사용자1", "정말 맛있어요!", rememberVectorPainter(Icons.Filled.AccountCircle)),
        Review("사용자2", "최고의 레시피입니다.", rememberVectorPainter(Icons.Filled.AccountCircle)),
        Review("사용자3", "다시 주문하고 싶어요!", rememberVectorPainter(Icons.Filled.AccountCircle))
        // Add more dummy reviews as needed
    )
}

@Composable
fun ReviewList(reviews: List<Review>) {
    Column {
        reviews.forEach { review ->
            ReviewCard(
                nickname = review.nickname,
                reviewContent = review.reviewContent,
                profileImage = review.profileImage,
                onEditClicked = { /* Handle edit click */ },
                onDeleteClicked = { /* Handle delete click */ }
            )
        }
    }
}

@Composable
fun ReviewSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ReviewInputColor, shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = { /* Handle review submit */ },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "등록")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        ReviewList(reviews = ReviewDummyList())
    }
}


@Composable
fun CustomOutlinedTextField(modifier: Modifier = Modifier) {
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
    )

    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("댓글을 남겨보세요") },
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = colors
    )
}
