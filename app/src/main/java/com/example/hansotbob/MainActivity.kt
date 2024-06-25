package com.example.hansotbob

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.hansotbob.ui.screen.MainScreen
import com.example.hansotbob.ui.theme.HansotbobTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HansotbobTheme{
                MainScreen()
            }
        }

    }
}

@Composable
fun ComposableFunction() {
    Text("appbar content")
}
