

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme

@Composable
fun MealkitsDetailScreen(title: String, recruit: String, place: String, price: String) {
    val images = listOf(
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image, // 실제 이미지 리소스를 추가
        R.drawable.food_image // 실제 이미지 리소스를 추가
    )
    val pagerState = rememberPagerState(pageCount = { images.size })

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
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
                ButtonRow()
            }
        }
    )
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
        HorizontalDivider(
            color = Color.Gray, // 더 연하게 수정
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp) // 좌우 패딩 추가
        )

        Row(
            // Merge semantics so accessibility services consider this row a single element
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(16.dp)
                .semantics(mergeDescendants = true) {}
        ) {
            Image(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null, // decorative
                modifier = Modifier.size(40.dp),
                colorFilter = ColorFilter.tint(LocalContentColor.current),
                contentScale = ContentScale.Fit
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

//@Composable
//fun RatingBar() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.padding(horizontal = 16.dp)
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.ic_star),
//            contentDescription = null,
//            tint = MaterialTheme.colorScheme.primary
//        )
//        Spacer(modifier = Modifier.width(4.dp))
//        Text(
//            text = "5.0 (2k)",
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//    }
//}

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
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("댓글쓰기") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* Handle review submit */ },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "댓글 등록")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        ReviewList()
    }
}

@Composable
fun ReviewList() {
    // Placeholder for RecyclerView equivalent
    Text("Review 1")
    Text("Review 2")
}

@Composable
fun ButtonRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = { /* Contact Seller */ },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("판매자에게 연락")
        }
        Button(
            onClick = { /* Buy */ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("구매")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMealkitDetailScreen() {
    HansotbobTheme {
        MealkitsDetailScreen("집밥1", "모집중", "시흥시 정왕동 산기대학로", "3000")
    }
}