package com.example.hansotbob.ui


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hansotbob.R


class ShareRegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_share_register)
        adjustImageViewsToSquare()
    }

    /* 화면에 맞춰 정사각형으로 만듬*/
    private fun adjustImageViewsToSquare() {
        val imageView1 = findViewById<ImageView>(R.id.imageView1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageView4 = findViewById<ImageView>(R.id.imageView4)

        // Assuming there are 4 images in a row and the screen width should be divided by 4.
        // This does not take padding or margin into account for simplicity.
        val screenWidth = resources.displayMetrics.widthPixels
        val imageSize = screenWidth / 5

        val layoutParams = imageView1.layoutParams
        layoutParams.height = imageSize
        imageView1.layoutParams = layoutParams
        imageView2.layoutParams = layoutParams
        imageView3.layoutParams = layoutParams
        imageView4.layoutParams = layoutParams
    }
}

