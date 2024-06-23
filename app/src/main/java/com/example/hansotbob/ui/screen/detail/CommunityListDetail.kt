
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                CommunityDetailCardWithBadge(
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
