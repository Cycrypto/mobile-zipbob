package com.example.hansotbob.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.example.hansotbob.viewmodel.screen.HomeFoodScreenViewModel

@Composable
fun FoodShareDetailScreen(
    navController: NavHostController,
    itemId: String,
    viewModel: HomeFoodScreenViewModel = viewModel()
) {
    val items by viewModel.items.collectAsState()
    val item = items.find { it.itemId == itemId }
    Log.d("FoodShareDetailScreen", "${item}")

    if (item != null) {
        val pagerState = rememberPagerState(pageCount = { 3 })

        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) { page ->
                    Image(
                        painter = painterResource(id = item.imagePainterId),
                        contentDescription = "Image $page",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconWithText(iconId = R.drawable.ic_add, text = "5.0 (2k)")
                    IconWithText(iconId = R.drawable.ic_add, text = "80 Cal")
                    IconWithText(iconId = R.drawable.ic_add, text = "Fri 26.12")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Recruit: ${item.recruit}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Place: ${item.place}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Price: ${item.price}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO: Add to Cart */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Add to Cart", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun IconWithText(iconId: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Preview
@Composable
private fun PreviewDetailScreen() {
    HansotbobTheme {
        val navController = rememberNavController()
        FoodShareDetailScreen(
            navController = navController,
            itemId = "dummyId"
        )
    }
}
