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

@Composable
fun CommunityCardWithBadge(
    title: String,
    imagePainter: Painter,
    currentPeople: Int,
    totalPeople: Int,
    points: Int,
    location: String,
    isNew: Boolean = false,
    modifier: Modifier = Modifier
) {
    val perPersonCost = if (totalPeople != 0) points / totalPeople else 0

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
                            text = "$currentPeople / $totalPeople",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { /* TODO: 참가하기 버튼 클릭 처리 */ },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .size(width = 80.dp, height = 40.dp)
                        ) {
                            Text(
                                text = "참가하기",
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
        val formData = CommunityFormData(
            title = "Hello",
            totalCost = "1000",
            participants = "4",
            location = "서울"
        )
        CommunityCardWithBadge(
            title = formData.title,
            imagePainter = painterResource(id = R.drawable.food_image),
            currentPeople = 2,
            totalPeople = formData.participants.toIntOrNull() ?: 0,
            points = formData.totalCost.toIntOrNull() ?: 0,
            location = formData.location,
            isNew = true
        )
    }
}
