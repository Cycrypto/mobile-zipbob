package com.example.hansotbob.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hansotbob.R

class CommunityRegisterActivity : AppCompatActivity() {

    companion object {
        private const val PICK_IMAGE = 1
    }

    private var selectedImageView: ImageView? = null

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

        // Set click listeners for ImageViews
        findViewById<ImageView>(R.id.imageView1).setOnClickListener { onImageClick(it) }
        findViewById<ImageView>(R.id.imageView2).setOnClickListener { onImageClick(it) }
        findViewById<ImageView>(R.id.imageView3).setOnClickListener { onImageClick(it) }
        findViewById<ImageView>(R.id.imageView4).setOnClickListener { onImageClick(it) }
    }

    private fun adjustImageViewsToSquare() {
        val imageView1 = findViewById<ImageView>(R.id.imageView1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageView4 = findViewById<ImageView>(R.id.imageView4)

        val screenWidth = resources.displayMetrics.widthPixels
        val imageSize = screenWidth / 5

        val layoutParams = imageView1.layoutParams
        layoutParams.height = imageSize
        imageView1.layoutParams = layoutParams
        imageView2.layoutParams = layoutParams
        imageView3.layoutParams = layoutParams
        imageView4.layoutParams = layoutParams
    }

    fun onImageClick(view: View) {
        selectedImageView = view as ImageView
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE)  
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            imageUri?.let {
                selectedImageView?.setImageURI(it)
            }
        }
    }
}
