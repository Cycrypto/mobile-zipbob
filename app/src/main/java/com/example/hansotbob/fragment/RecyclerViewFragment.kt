package com.example.hansotbob.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hansotbob.R
import com.example.hansotbob.TestRecyclerViewAdapter
import com.github.florent37.materialviewpager.MaterialViewPagerHelper
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

class RecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): RecyclerViewFragment {
            return RecyclerViewFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)

        val items: MutableList<Any> = ArrayList()
        for (i in 0 until 50) {
            items.add(Any())
        }

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = TestRecyclerViewAdapter(items)

        // 아이템 간의 간격 추가
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 10
                outRect.bottom = 10
            }
        })

        // onScrollListener 추가
        val onScrollListener = object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 스크롤 이벤트 처리 로직 추가
            }
        }

        // RecyclerView 등록
        MaterialViewPagerHelper.registerRecyclerView(requireActivity(), recyclerView, onScrollListener)
    }
}