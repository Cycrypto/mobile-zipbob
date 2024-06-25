package com.example.hansotbob

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme
import com.example.hansotbob.viewmodel.form.IngredientFormViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hansotbob.viewmodel.screen.IngredientShreViewModel

@Composable
fun CommunityCardWithBadge(
    viewModel: IngredientShreViewModel = viewModel(),
    itemId: String,
    title: String,
    author: String,
    imagePainter: Painter,
    initialCurrentPeople: String = "0",
    totalPeople: String,
    points: String,
    location: String,
    isNew: Boolean = false,
    modifier: Modifier = Modifier
) {
    var currentPeople by remember { mutableIntStateOf(initialCurrentPeople.toInt()) }
    val partyState by viewModel.partyState.collectAsState()
    val state = partyState[itemId]

    // 상태가 없으면 업데이트를 시도하고 로딩 상태를 표시
    if (state == null) {
        LaunchedEffect(itemId) {
            viewModel.updatePartyState(itemId)
        }
        Text("Loading...")
        return
    }

    LaunchedEffect(state.currentPeople) {
        currentPeople = state.currentPeople
    }

    val totalPeopleInt = totalPeople.toIntOrNull() ?: 1
    val pointsInt = points.toInt()
    val perPersonCost = if (totalPeopleInt != 0) pointsInt / totalPeopleInt else 0

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp)
            ) {
                BadgeBox2(
                    badge = {
                        if (isNew) {
                            Badge(
                                modifier = Modifier
                                    .offset(-2.dp, (-4).dp)
                                    .size(20.dp),
                                content = {
                                    Text(
                                        text = "N",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                                    )
                                },
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                ) {
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.onBackground),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "인당 $perPersonCost 원",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Bottom)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$currentPeople / $totalPeopleInt",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { viewModel.joinItem(itemId) },
                            enabled = !state.isAuthor && !state.hasJoined && state.currentPeople < state.totalPeople,
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .size(width = 80.dp, height = 40.dp)
                        ) {
                            Text(
                                text = "참가",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun BadgeBox2(
    badge: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        content()
        badge()
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewCommunityCard3() {
    HansotbobTheme {
        CommunityCardWithBadge(
            title = "Hello",
            itemId="1",
            author = "a",
            imagePainter = painterResource(id = R.drawable.food_image),
            totalPeople = "4",
            points = "1000",
            location = "서울",
            isNew = true
        )
    }
}
