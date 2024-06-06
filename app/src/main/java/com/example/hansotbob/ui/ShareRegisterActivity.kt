package com.example.hansotbob.ui


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hansotbob.MainActivity
import com.example.hansotbob.R
import java.util.Calendar


class ShareRegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_share_register)
        adjustImageViewsToSquare()

        /* 버튼 등록 */
        val submitButton: Button = findViewById(R.id.share_submit_button)
        submitButton.setOnClickListener {
            // 임시로 MainActivity로 이동하는 인텐트 생성
            val intent = Intent(this, MainActivity::class.java)

            // MainActivity로 이동
            startActivity(intent)
            finish()
        }

        /* date 입력을 위한 코드 */
        val dateEditText: EditText = findViewById(R.id.dateEditText)

        dateEditText.setOnClickListener {
            // 현재 날짜를 가져오기 위해 Calendar 객체 생성
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            // DatePickerDialog를 생성하고 날짜 선택기를 표시
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // 선택한 날짜를 EditText에 설정
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                dateEditText.setText(selectedDate)
            }, year, month, dayOfMonth)

            // 선택 가능한 최소 날짜 설정 (예: 오늘 날짜)
            datePickerDialog.datePicker.maxDate = calendar.timeInMillis

            // DatePickerDialog 표시
            datePickerDialog.show()
        }


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

