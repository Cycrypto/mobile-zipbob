package com.example.hansotbob.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.hansotbob.R
import com.example.hansotbob.component.CardView.ReviewCard
import com.example.hansotbob.ui.theme.HansotbobTheme

@Composable
fun RecipeAppScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            CategoryRow()
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            TrendingRecipes()
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            RecommendedRecipes()
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            ReviewSection()
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "dish name") },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon"
            )
        }
    )
}

@Composable
fun CategoryRow() {
    val categories = listOf("한식", "중식", "일식", "양식", "분식")
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories.size) { index ->
            Button(
                onClick = {},
//                colors = ButtonDefaults.buttonColors(back = Color.LightGray),
                shape = CircleShape,
                modifier = Modifier
                    .height(40.dp)
                    .clip(CircleShape)
            ) {
                Text(text = categories[index])
            }
        }
    }
}

@Composable
fun TrendingRecipes() {
    Column {
        Text(
            text = "트렌드",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(2) { index ->
                RecipeCard(
                    recipeName = if (index == 0) "밥솥없이 밥 짓는 법" else "불고기 밀키트 레시피",
                    rating = if (index == 0) "9.0" else "8.5",
                    imageUrl = if (index == 0)
                        "https://d2qgx4jylglh9c.cloudfront.net/kr/wp-content/uploads/2017/06/2316BC3C593E31BD2A73A8.jpg"
                        else "https://d2qgx4jylglh9c.cloudfront.net/kr/wp-content/uploads/2017/06/240FC934593E3B0701F0E1.jpg"
                )
            }
        }
    }
}


@Composable
fun RecipeCard(recipeName: String, rating: String, imageUrl: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(180.dp)
            .padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = recipeName,
                contentScale = ContentScale.Crop, // 이미지 크기를 맞춤
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)) // 이미지도 둥글게 처리
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = recipeName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "★ $rating", color = Color.Gray)
        }
    }
}


@Composable
fun RecommendedRecipes() {
    Column {
        Text(
            text = "요리 꿀팁",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(2) { index ->
                RecipeCard(
                    recipeName = if (index == 0) "칼질 기본" else "파프리카 손질법",
                    rating = if (index == 0) "9.5" else "8.8",
                    imageUrl = if (index == 0)
                        "https://d2qgx4jylglh9c.cloudfront.net/kr/wp-content/uploads/2017/06/2316BC3C593E31BD2A73A8.jpg"
                    else "https://d2qgx4jylglh9c.cloudfront.net/kr/wp-content/uploads/2017/06/240FC934593E3B0701F0E1.jpg"
                )
            }
        }
    }
}

@Composable
fun ReviewSection() {
    Column {
        Text(
            text = "Reviews",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        ReviewCard(
            nickname = "그저 그런",
            reviewContent = "레시피가 맛있고 주인장이 친절해요",
            rating = 4.5f,
            profileImageUrl = "https://i.namu.wiki/i/0bpXUy1sCayiy-mODi-8wH5hL7yfgXXyz0IK8o-EskfacnmZB5_mwfH8aT3l2pnXLQO_qW4dAVSJFrjY1hVAAQ.webp",
            onEditClicked = { /* Handle edit */ },
            onDeleteClicked = { /* Handle delete */ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ReviewCard(
            nickname = "sudo rm -rf /",
            reviewContent = "조금 짠듯",
            rating = 3.0f,
            profileImageUrl = "data:image/webp;base64,UklGRpQHAABXRUJQVlA4IIgHAAAQLQCdASq3AHkAPo0+mkglI6KhLhVaOKARiWcDsB4O8Qe8uhbPHuDmj/m8U7PsXBzxhlMNd4q/2SbEZ0FLdeW2P7twCVfQP/0/nQ+rzLcAGe0I0kAgJVNJQ7eCat1hl0hSWizTEKgBideqO8HiGq3Zd3EGRtwEbpwABQVqXQlqtkYmqWz667VwsfShMQuXzhLoQTgUUDP+7pQXtxSNmjWAjErLNqFAcqDmf7849XGxOvGE/IRJZZ9PDU+L3vRs4bc3/yV56FriQe1ydO9n68wc+gIu9h5qxOV7FQ2jTMg7axD2T9/V6IMrMma1/R6vzuk7Enwhz5C63L+Wr2DOtssud0eBpOJFx9ZIJOkzueS6Agtp+jMa6QaaK/rMXJCicXWg9BM9l+tRCw3kI9UC1bD+18++xBwyqsr4edQeQ7cm9UT9Z0juM489A6s4W3iIDEMjVh4l3BzWNSXegIjSbDegfu96J7YZjH53qu0t/MYl7AAA/ukiEI0h33HVFxjri0YGhuFuwKfHctOf9wXxg8H/HL/JnFU5/cjoRTPSaXMoWbRa/TTjT4wVi+6vaUq4ZrD2emGbLQgcLamntDtFYY7uKQGa0AjskFKDBma7vPF/DbRRHVTtQUDa3J41OWF2sbPs8PE5/IPVvfahsNjKRIgperuI6xN+HL65Jv1+CK59boDKZjO4QZGZ1bnAlem7o22xE5Z8p5hE1Dyfjdu/JutnxOym9Lcf8wqEbEELD1+xJFujODZZJnkBQYBoritAVip/6abEcZ1Cp5DpEQO/YfjFGJsiB+nLjA7IwQi0Ah/Y4rolfhAXPGjtJqe3XzvaxQ50FyQ642u8qMKBBtmqQxOKjg/okvwxx/8Q3nNn8X6C8vqmjaR43wxL7iyGsqxIr3k+wgHrCbKaOmxK33p1CYYsZ9RT3FaIDh3uf8l7nAXEcfZ/1qxc4jNsDw4r5PoK2NFxTAJRcJ2Kf6Y9ImYAHhYn5jsApyyT4VY0kXJ+/VBkXQigm+l3u7/ClMMYpIyCzzRYjJ+7ukzmGfUM3qES/I5KBub8Dfsf2T/fWuXh4ZgJi1eojzDJI2Dfzxz5+QyCG0Iybhwuoqz7yQt26qwBPZcdzPuxbSRRjjS+Lw8ASwM35mhnMB4mZxqZ8puANfkqSohL4YNv5nHyXSD+DU2cQNA7H0A4Lpw2Fb8iY9bhcZgObqy7XZTQSi/79AmEbMeMvb/7mVNsXPR+BXZfWisOxuAt1RuOtL/GUxGm2Qa/GPE5Jm3sh3G9QRWKLnDZxSwWGwX80Rbk+rGkwGgShoMYeIfH8XZTDGD7UfGw8zt09S3iEU6TBjDzKY11B19UyAMZlJj+D9m86qO/V4NZAW6TgysctZZ1mv4sckNnH7eUN/5FK3yR9ZXmjfH+zzWJXanDKauzrCjGR2SnbLX1/U6wqueT8rBue+1IpXO/SOuZ6c2mtJPNsOXSjf6SPOy9sEyuBj9Ori24izmRb9tiNQvCCuYR91fzOJqF/z7d6rKQKoIGSDtimk6BXgbAFF0idALczwpulvn5xGJMeWjxWJD+VoxeCtSdxLgh2PtPKrtrltdPDQMjgel7qIKMCBfgT7b0ZgD05npTYm0HhC28vTgTqOiOzMTxn7y0m11mQBXQZ5Vk/5ZzNiC4k8Od0m36TGdGHnhxKV0viF1zOTUVnc5TCRtLDIFpZuK0Oy+U65cbh3x2IItkfi9EXaFUW85Nv2QssCtldNxo3eanUTpI2iUdqxLQR/IgkbRqfecNAgV+ZCCxroXJk7TAmRqyr4odk9p6vUX2omRdJIya5/bTafwKO+3QB2p3zGsFk1aVbkgItPOPCZoXQS3kbeWDezR+rBzXY76/v/qNLjwkNOau/hyx9/jGfPK60rCvxt51I+8MOKiX5rGX0rUbTDeD0lxq8pWZ/9n+T9NtyvxZnQZJHyg4/J5TPN8j3GeK0gn0tEVL8LgXlidLVbIXc6iJjYrEQLg8lnHdOIvqIBcmh6E58LEk5U1+xaqg39HSJB5Gd2rKtPxzZDIfXF6audOCRm5Xmfcc2B01BjKIimnDxPVyDF4s/lM3Fu0r+1K1cpSqHgE8083WQIzWg7Pmdl7SdUze9RwJUfJdgTJEEsdk+0nkVzpHVudDaockqV45PX6aplxfeWpFGqdKlJCFqB+FE7IOjNih43dYdPZMR4iH7c5AqMeQercnDfzE+ZFtU5PRoDGT8bwiAicxD21ilYkhaoOMW1LoVgQfVEdx9zj8OHUHcd46GiUczUANu2c87pseWEAvIJi6xEFldUrnKCd6OFT4a5kHxBAu3FaS2nmvE3nYfqMq+328g+tdIVVxK7ynSaqiPdm/IH9OcMu9pADtN2+IRECD3w0MLeX1JHnDja5Oe0oHIuquSrerSRhTRpFXSHiwfUSQ9Z15AfPMEZMTByMdI+pgNnXJDw1rL7n41CSmjNRD8+vBX33J+ebYHwBU3GjepbO6PI9bWAlzWrZcHVOvnb4DsVYeL8YdltyufqvwNwQQNSCvKWTpl/MCybdDWrZDaeeqXaYFxKtlL1Eahp8harCcIqXsvpGnjJEAAA==",
            onEditClicked = { /* Handle edit */ },
            onDeleteClicked = { /* Handle delete */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HansotbobTheme {
        val navController:NavController = rememberNavController()
        RecipeAppScreen(navController)
    }
}