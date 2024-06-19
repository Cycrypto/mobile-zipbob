package com.example.hansotbob.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.component.CardView.OverviewCard
import com.example.hansotbob.component.Slider.CardSliderHorizontal
import com.example.hansotbob.dto.ListItemDTO
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.example.hansotbob.viewmodel.ListViewModel

@Composable
fun OverviewScreen(navController: NavHostController, viewModel: ListViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()

    Column {
        TopProfileSection()
        CardSliderHorizontal(
            title = "인기 게시물",
            item = items.filterIsInstance<ListItemDTO.Overview>()
        ) { overviewItem ->
            OverviewCard(
                imageRes = overviewItem.imageRes,
                name = overviewItem.name,
                category = overviewItem.category,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}
@Composable
fun TopProfileSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.mealkits_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("한솥밥",
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text("시흥시 공유자",
                color = MaterialTheme.colorScheme.background,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Default.Notifications,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.background
        )
    }
}

@Preview
@Composable
fun PreviewOverviewScreen() {
    val navController = rememberNavController()
    HansotbobTheme {
        OverviewScreen(navController)
    }
}