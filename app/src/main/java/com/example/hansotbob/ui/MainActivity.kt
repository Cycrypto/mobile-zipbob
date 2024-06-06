package com.example.hansotbob.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }
}
