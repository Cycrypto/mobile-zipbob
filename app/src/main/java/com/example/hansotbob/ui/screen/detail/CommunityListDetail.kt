import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme



@Composable
fun CommunityListDetail(navController: NavHostController, cards: List<CommunityCardData>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            cards.forEach { card ->
                CommunityCardWithBadge(
                    title = card.title,
                    imagePainter = card.imagePainter,
                    currentPeople = card.currentPeople,
                    totalPeople = card.totalPeople,
                    points = card.points,
                    location = card.location,
                    isNew = card.isNew,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "돌아가기", color = Color.White)
        }
    }
}

data class CommunityCardData(
    val title: String,
    val imagePainter: Painter,
    val currentPeople: Int,
    val totalPeople: Int,
    val points: Int,
    val location: String,
    val isNew: Boolean = false
)

@Preview(showBackground = true)
@Composable
fun PreviewCommunityListDetail() {
    HansotbobTheme {
        val navController = rememberNavController()
        val sampleData = listOf(
            CommunityCardData(
                title = "Hello",
                imagePainter = painterResource(id = R.drawable.food_image),
                currentPeople = 2,
                totalPeople = 4,
                points = 1000,
                location = "서울",
                isNew = true
            ),
            CommunityCardData(
                title = "World",
                imagePainter = painterResource(id = R.drawable.food_image),
                currentPeople = 1,
                totalPeople = 3,
                points = 1500,
                location = "부산",
                isNew = false
            )
        )
        CommunityListDetail(navController = navController, cards = sampleData)
    }
}
