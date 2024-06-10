package com.example.hansotbob.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.hansotbob.R
import com.example.hansotbob.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.foodImageSlider
        val imageUrls = mutableListOf<String>()

        // 이미지 URL이 없을 경우 기본 이미지 추가
        if (imageUrls.isEmpty()) {
            imageUrls.add("android.resource://${packageName}/${R.drawable.temp_nonimage}")
        }

    }

    /* 아래는 imageview 확인용 */
    class ImageAdapter(private val context: Context, private val imageUrls: List<String>) :
        RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

        inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.imageView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.review_layout, parent, false)
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