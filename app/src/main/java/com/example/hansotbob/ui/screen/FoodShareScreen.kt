import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hansotbob.R


@Composable
fun <T>CardSliderVertical(title :String, items: List<T>, itemContent: @Composable (T) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                itemContent(item)
            }
        }
    }
}

@Composable
fun <T>CardSliderHorizontal(title :String, items: List<T>, itemContent: @Composable (T) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                itemContent(item)
            }
        }
    }
}

@Composable
fun DeviceCard(device: com.example.hansotbob.ui.screen.Device) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .width(200.dp)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()  // Ensure the column fills the width of the card
                .background(MaterialTheme.colorScheme.background)
        ) {
            Image(
                painter = painterResource(id = device.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,  // Adjust image to fill the width
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()  // Ensure this column also fills the width
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(device.name)
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favorites",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(device.category, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

data class Device(val name: String, val category: String, val imageRes: Int)

private fun createDummyData(): List<Device> {
    return listOf(
        Device("밥 가져가실분", "한식", R.drawable.community_image),
        Device("밥 가져가실분", "한식", R.drawable.community_image),
        Device("밥 가져가실분", "한식", R.drawable.community_image),
        Device("밥 가져가실분", "한식", R.drawable.community_image),
        Device("밥 가져가실분", "한식", R.drawable.community_image)
    )
}