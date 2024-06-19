package com.example.hansotbob.ui.activity

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.hansotbob.R
import com.example.hansotbob.adapter.ReviewAdapter
import com.example.hansotbob.dto.Review
import com.example.hansotbob.databinding.ActivityMealkitDetailBinding

class MealkitDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMealkitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.foodImageSlider
        val imageUrls = mutableListOf<String>()

        // 이미지 URL이 없을 경우 기본 이미지 추가
        if (imageUrls.isEmpty()) {
            imageUrls.add("android.resource://${packageName}/${R.drawable.temp_nonimage}")
        }


        val adapter = ImageAdapter(this, imageUrls)
        viewPager.adapter = adapter


        // 리뷰부분

        val reviewList = mutableListOf<Review>()

        val reviewAdapter = ReviewAdapter(reviewList)
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.reviewRecyclerView.adapter = reviewAdapter

        binding.submitReviewButton.setOnClickListener {
            val reviewText = binding.reviewEditText.text.toString()
            if (!TextUtils.isEmpty(reviewText)) {
                val review = Review("닉네임", reviewText)
                reviewList.add(review)
                reviewAdapter.notifyItemInserted(reviewList.size - 1)
                binding.reviewEditText.text.clear()
            } else {
                Toast.makeText(this, "리뷰를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        val dummyReviews = listOf(
            Review("유저1", "이 앱 정말 좋아요!"),
            Review("유저2", "디자인이 너무 멋져요."),
            Review("유저3", "기능이 다양해서 유용해요.")
        )

        reviewList.addAll(dummyReviews)
        reviewAdapter.notifyDataSetChanged()

        // 등록 버튼


    }


    /* 아래는 imageview 확인용 */
    class ImageAdapter(private val context: Context, private val imageUrls: List<String>) :
        RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

        inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.imageView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.review_imagelayout, parent, false)
            return ImageViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

            val testImageUrl =
                "https://www.seriouseats.com/thmb/sNOqOuOaiILj05PSuunyT3FuyPY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Filipino-Features-Soups-and-Stews-1e81ba12ce10481caf3ff58981c347ab.jpg"

            //val imageUrl = imageUrls[position]
            Glide.with(context).load(testImageUrl).into(holder.imageView)
        }

        override fun getItemCount(): Int = imageUrls.size
    }
}




