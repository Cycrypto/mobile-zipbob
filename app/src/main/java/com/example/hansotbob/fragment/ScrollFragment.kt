package com.example.hansotbob.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hansotbob.R
import com.github.florent37.materialviewpager.MaterialViewPagerHelper
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState

class ScrollFragment : Fragment(), ObservableScrollViewCallbacks {

    private lateinit var mScrollView: ObservableScrollView

    companion object {
        fun newInstance(): ScrollFragment {
            return ScrollFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_scroll, container, false)
        mScrollView = view.findViewById(R.id.scrollView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MaterialViewPagerHelper.registerScrollView(requireActivity(), mScrollView, this)
    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
        // 스크롤 변경 시 처리할 로직
    }

    override fun onDownMotionEvent() {
        // 다운 모션 이벤트 처리 로직
    }

    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
        // 업 또는 취소 모션 이벤트 처리 로직
    }
}