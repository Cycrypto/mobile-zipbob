package com.example.hansotbob.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.hansotbob.R
import com.example.hansotbob.fragment.RestaurantListFragment
import com.example.hansotbob.ui.theme.HansotbobTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RestaurantListFragment를 추가
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RestaurantListFragment())
                .commit()
        }

        // ComposeView를 사용하여 Compose Navigation 추가
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            HansotbobTheme {
                NavigationBar()
            }
        }
    }
}

@Composable
fun NavigationBar() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        BottomAppBar(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Home", modifier = Modifier.padding(16.dp))
            Text(text = "Search", modifier = Modifier.padding(16.dp))
            Text(text = "Profile", modifier = Modifier.padding(16.dp))
        }
    }
}
