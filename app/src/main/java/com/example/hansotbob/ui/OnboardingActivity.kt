package com.example.hansotbob.ui

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.example.hansotbob.R

class OnboardingActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private lateinit var viewFlipper: ViewFlipper
    private lateinit var nextArrow: ImageView
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewFlipper = findViewById(R.id.viewFlipper)
        nextArrow = findViewById(R.id.next_arrow)
        gestureDetector = GestureDetector(this, this)

        nextArrow.setOnClickListener {
            viewFlipper.showNext()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { gestureDetector.onTouchEvent(it) }
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent) {}

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {}

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1 != null && e2 != null) {
            if (e1.x - e2.x > 100) {
                viewFlipper.showNext()
                return true
            } else if (e2.x - e1.x > 100) {
                viewFlipper.showPrevious()
                return true
            }
        }
        return false
    }
}
