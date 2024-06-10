package com.example.hansotbob.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hansotbob.MainActivity
import com.example.hansotbob.R

class CommunityRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_community_register)

        adjustImageViewsToSquare()

        val submitButton: Button = findViewById(R.id.share_submit_button)
        submitButton.setOnClickListener {
            // 임시로 MainActivity로 이동하는 인텐트 생성
            val intent = Intent(this, MainActivity::class.java)

            // MainActivity로 이동
            startActivity(intent)
            finish()

        }
    }

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