package com.example.hansotbob.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hansotbob.R
import com.example.hansotbob.adapter.RestaurantAdapter
import com.example.hansotbob.data.Restaurant

class RestaurantListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantAdapter
    private lateinit var restaurantList: MutableList<Restaurant>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 인플레이트
        val view = inflater.inflate(R.layout.fragment_restaurant_list, container, false)
        recyclerView = view.findViewById(R.id.restaurant_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RestaurantAdapter(createDummy())
        recyclerView.adapter = adapter

        return view
    }

    private fun createDummy(): MutableList<Restaurant> {
        restaurantList = mutableListOf()
        for (i in 1..50){
            restaurantList.add(Restaurant(R.drawable.mealkits_image, "우리집 레시피로 밀키트를 만들었어요", "4500 pts • 경기도 시흥시 정왕1동 행정복지센터"))
        }
        return restaurantList
    }
}
