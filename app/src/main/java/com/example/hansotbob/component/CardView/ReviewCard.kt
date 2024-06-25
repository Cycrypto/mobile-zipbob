package com.example.hansotbob.component.CardView

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.hansotbob.R
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun ReviewCard(
    nickname: String,
    reviewContent: String,
    rating: Float,
    profileImageUrl: String,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    val imagePainter = if (profileImageUrl == "__NULL__") {
        painterResource(id = R.drawable.person_icon)
    } else {
        rememberAsyncImagePainter(
            model = profileImageUrl,
            error = painterResource(id = R.drawable.person_icon)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(Color.White),
        shape = RoundedCornerShape(0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = imagePainter,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Transparent),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = nickname,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                    RatingBar(
                        value = rating,
                        style = RatingBarStyle.Stroke(),
                        size = 20.dp,
                        spaceBetween = 2.dp,
                        modifier = Modifier.padding(start = 10.dp),
                        onValueChange = {
                        },
                        onRatingChanged = {
                            Log.d("TAG", "onRatingChanged: $it")
                        }
                    )
                }
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreHoriz,
                            contentDescription = "Menu"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("수정") },
                            onClick = {
                                onEditClicked()
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("삭제") },
                            onClick = {
                                onDeleteClicked()
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = reviewContent,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 8.dp)
            )
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewCardPreview() {
    ReviewCard(
        nickname = "닉네임",
        reviewContent = "최고의 레시피 추천합니다 ^^,,",
        rating = 4.0f,
        profileImageUrl = "https://images.pexels.com/photos/1458914/pexels-photo-1458914.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        onEditClicked = { /* Handle edit click */ },
        onDeleteClicked = { /* Handle delete click */ }
    )
}
