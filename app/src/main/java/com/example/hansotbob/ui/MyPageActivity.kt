package com.example.hansotbob.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hansotbob.R
import com.example.hansotbob.adapter.TransactionAdapter
import com.example.hansotbob.data.Transaction

class MyPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        // 더미 데이터 설정
        val transactions = listOf(
            Transaction("포인트 적립 내역"),
            Transaction("관심목록"),
            Transaction("구매내역"),
            Transaction("판매내역"),
            Transaction("내가 쓴 리뷰"),
            Transaction("내가 받은 리뷰")
        )

        // RecyclerView 설정
        val transactionList = findViewById<RecyclerView>(R.id.transaction_list)
        transactionList.layoutManager = LinearLayoutManager(this)
        transactionList.adapter = TransactionAdapter(transactions)
    }
}