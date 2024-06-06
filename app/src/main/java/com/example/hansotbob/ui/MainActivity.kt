package com.example.hansotbob.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.hansotbob.R
import com.example.hansotbob.fragment.RestaurantListFragment

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
            NavigationBar()
        }
    }
}

@Composable
fun NavigationBar() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        BottomAppBar(
            modifier = Modifier.padding(8.dp)
        ) {
            // 네비게이션 항목
            Text(text = "Home", modifier = Modifier.padding(16.dp))
            Text(text = "Search", modifier = Modifier.padding(16.dp))
            Text(text = "Profile", modifier = Modifier.padding(16.dp))
        }
    }
}