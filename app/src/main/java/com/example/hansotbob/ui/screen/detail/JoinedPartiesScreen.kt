
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hansotbob.R
import com.example.hansotbob.viewmodel.screen.IngredientShreViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun JoinedPartiesScreen(navController: NavHostController, viewModel: IngredientShreViewModel = viewModel()) {
    val joinedParties by viewModel.joinedParties.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadJoinedParties()
    }

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
            joinedParties.forEach { party ->
                CommunityDetailCardWithBadge(
                    title = party.title,
                    imagePainter = painterResource(id = R.drawable.food_image), // 실제 이미지 리소스를 제공해야 함
                    currentPeople = party.currentPeople.toInt(),
                    totalPeople = party.totalPeople.toInt(),
                    points = party.totalCost.toInt(),
                    location = party.location,
                    isNew = false,
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