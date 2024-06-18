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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import com.example.hansotbob.component.common.AppBar
import com.example.hansotbob.ui.theme.ReviewInputColor
import com.example.hansotbob.component.common.ButtonBar

@Composable
fun MealkitsDetailScreen(
    title: String,
    recruit: String,
    place: String,
    price: String,
    onBackClick: () -> Unit,
    onContactSellerClick: () -> Unit,
    onBuyClick: () -> Unit
) {
    val images = listOf(
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image // 실제 이미지 리소스를 추가
    )
    val pagerState = rememberPagerState(pageCount = { images.size })

    Scaffold(
        topBar = { AppBar(onBackClick = onBackClick) },
        bottomBar = { ButtonBar(onContactSellerClick = onContactSellerClick, onBuyClick = onBuyClick) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 0.dp)
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
            Spacer(modifier = Modifier.height(16.dp))
            MealkitContent(title = title, recruit = recruit, place = place, price = price)
            Spacer(modifier = Modifier.height(16.dp))
            PostMetadata(
                metadata = Metadata(
                    author = Author("John Doe"),
                    date = "June 18, 2024",
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            //RatingBar()
            Spacer(modifier = Modifier.height(16.dp))
            ReviewSection()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MealkitContent(title: String, recruit: String, place: String, price: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Recruit: $recruit",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Place: $place",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Price: $price",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        val reviewText = "맛있는 저녁 식사 메뉴입니다."
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = reviewText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
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
            modifier = Modifier.padding(horizontal = 16.dp)
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
fun ReviewSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
        ReviewList()
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

@Composable
fun ReviewList() {
    // Placeholder for RecyclerView equivalent
    Text("Review 1")
    Text("Review 2")
}

@Preview(showBackground = true)
@Composable
fun PreviewMealkitDetailScreen() {
    HansotbobTheme {
        MealkitsDetailScreen(
            title = "집밥1",
            recruit = "모집중",
            place = "시흥시 정왕동 산기대학로",
            price = "3000",
            onBackClick = {},
            onContactSellerClick = {},
            onBuyClick = {}
        )
    }
}