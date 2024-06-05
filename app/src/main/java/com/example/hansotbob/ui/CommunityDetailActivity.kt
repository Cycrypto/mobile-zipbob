package com.example.hansotbob.ui

import Review
import ReviewAdapter
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.hansotbob.R
import com.example.hansotbob.databinding.ActivityCommunityDetailBinding

class CommunityDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reviewList = mutableListOf<Review>()

        val reviewAdapter = ReviewAdapter(reviewList)
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.reviewRecyclerView.adapter = reviewAdapter


        val dummyReviews = listOf(
            Review("https://www.seriouseats.com/thmb/sNOqOuOaiILj05PSuunyT3FuyPY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Filipino-Features-Soups-and-Stews-1e81ba12ce10481caf3ff58981c347ab.jpg", "10,000","2,000","고기","5","3"),
            Review("https://www.seriouseats.com/thmb/sNOqOuOaiILj05PSuunyT3FuyPY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Filipino-Features-Soups-and-Stews-1e81ba12ce10481caf3ff58981c347ab.jpg", "10,000","2,000","고기","5","3"),
            Review("https://www.seriouseats.com/thmb/sNOqOuOaiILj05PSuunyT3FuyPY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Filipino-Features-Soups-and-Stews-1e81ba12ce10481caf3ff58981c347ab.jpg", "10,000","2,000","고기","5","3"),
        )


        reviewList.addAll(dummyReviews)
        reviewAdapter.notifyDataSetChanged()

    }
}