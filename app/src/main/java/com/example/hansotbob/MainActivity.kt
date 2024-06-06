package com.example.hansotbob

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavGraph
import com.example.hansotbob.ui.screen.MainScreen
import com.example.hansotbob.ui.screen.NavGraph


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MaterialTheme{
                NavGraph()
            }
        }

    }
}

@Composable
fun ComposableFunction() {
    Text("appbar content")
}
