package com.example.hansotbob.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hansotbob.R
import com.example.hansotbob.adapter.CategoryAdapter
import com.example.hansotbob.dto.Category

class CategoryFragment : Fragment() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: MutableList<Category>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 인플레이트
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)
        categoryRecyclerView = view.findViewById(R.id.category_list)

        // 카테고리 데이터 추가
        categoryList = mutableListOf(
            Category(R.drawable.img_category_kor, "한식"),
            Category(R.drawable.img_category_kor, "분식"),
            Category(R.drawable.img_category_kor, "중식"),
            Category(R.drawable.img_category_kor, "일식"),
            Category(R.drawable.img_category_kor, "양식")
        )

        categoryRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryAdapter(categoryList)
        categoryRecyclerView.adapter = categoryAdapter

        // Centering the items
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(categoryRecyclerView)

        return view
    }
}